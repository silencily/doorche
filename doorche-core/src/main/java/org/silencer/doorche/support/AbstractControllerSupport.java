package org.silencer.doorche.support;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.silencer.doorche.context.Condition;
import org.silencer.doorche.context.ConditionContext;
import org.silencer.doorche.context.ConditionContextManager;
import org.silencer.doorche.context.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 控制器基类
 *
 * @author gejb
 * @since 2016/2/18
 */
public abstract class AbstractControllerSupport {
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 条件模型属性名称
     */
    public static final String MODEL_ATTRIBUTE_NAME_CONDITIONS = "conditions";

    /**
     * 条件上下文管理器
     */
    @Autowired
    private ConditionContextManager conditionContextManager;

    /**
     * 组装条件查询数据，并将其保存至条件上下文中供后期业务逻辑处理调用查询数据库<br>
     * 同时将条件数据保存至模型中供页面回显
     *
     * @param conditions form表单中查询条件
     * @param model      数据模型
     */
    @ModelAttribute
    public void populateConditions(ConditionsModel conditions, Model model) {
        Map<String, Condition> conditionMap = conditions.getConditions();
        List<Condition> lastConditions = new ArrayList<Condition>();
        ConditionContext conditionContext = new ConditionContext();
        for (Condition condition : conditionMap.values()) {
            String name = condition.getName();
            if (StringUtils.isNotBlank(name)) {
                //创建条件时，Condition.value属性仍然是form表单提交的string
                String value = (String) condition.getValue();
                Class type = condition.getType();
                if (type == null) {
                    type = Condition.DEFAULT_TYPE;
                }
                try {
                    Object val = BeanUtilsBean.getInstance().getConvertUtils().convert(value, type);
                    condition.setValue(val);
                    lastConditions.add(condition);
                } catch (Throwable e) {
                    String msg = "无法把条件[" + condition.getName() + "]值[" + condition.getValue() + "]转成类型[" + type.getName() + "]";
                    logger.error(msg, e);
                    throw new RuntimeException(msg, e);
                }
            }
        }
        Collections.sort(lastConditions);
        conditionContext.setConditions(lastConditions.toArray(new Condition[lastConditions.size()]));

        Paginator paginator = conditions.getPaginator();
        if (paginator.isNotPaginated()) {
            paginator = Paginator.NOT_PAGINATED;
        }
        conditionContext.setPaginator(paginator);

        conditionContextManager.setConditionContext(conditionContext);

        //保存条件模型
        model.addAttribute(MODEL_ATTRIBUTE_NAME_CONDITIONS, conditions);
    }

}
