package org.silencer.doorche.support;

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
    public static final String MODEL_ATTRIBUTE_CONDITIONS = "conditions";

    @Autowired
    private ConditionContextManager conditionContextManager;

    @ModelAttribute
    public void populateConditions(ConditionsModel conditions, Model model) {
        Map<String, Condition> conditionMap = conditions.getConditions();
        Paginator paginator = conditions.getPaginator();

        ConditionContext conditionContext = new ConditionContext();
        if (conditionMap != null) {
            for (Condition condition : conditionMap.values()) {
                String name = condition.getName();
                if (StringUtils.isNotBlank(name)) {
                    //创建条件时，Condition.value属性仍然是form表单提交的string
                    String value = (String)condition.getValue();
                    Class type =condition.getType();
                    if(type==null){
                        type = Condition.DEFAULT_TYPE;
                    }

                }


            }
        }


    }

}
