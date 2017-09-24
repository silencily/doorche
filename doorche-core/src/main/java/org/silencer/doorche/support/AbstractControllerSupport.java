package org.silencer.doorche.support;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.silencer.doorche.context.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
     * 重用查询条件属性名称，用于将查询条件保存至当前session，当查询时若指定重用查询将此查询条件直接取出</br>
     * 每次带有查询条件查询时会覆盖上一次缓存的查询条件，重用查询条件值保存一份
     */
    public static final String SESSION_ATTRIBUTE_NAME_RECONDITION = "recondition";

    /**
     * 条件上下文管理器
     */
    @Autowired
    private ConditionContextManager conditionContextManager;

    /**
     * 组装条件查询数据，并将其保存至条件上下文中供后期业务逻辑处理调用查询数据库<br>
     * 同时将条件数据保存至模型中供页面回显<br>
     * 系统默认规定两层请求地址将自动设置分页需求，并设置<code>paginator.page=0</code>
     *
     * @param conditions form表单中查询条件
     * @param model      数据模型
     */
    @ModelAttribute
    public void populateConditions(ConditionsModel conditions, Model model, HttpServletRequest request) {
        ConditionsModel conditionsModel = conditions;
        boolean recondition = conditions.isRecondition();
        if (recondition) {
            String servletPath = request.getServletPath();
            Map<String, ConditionsModel> reconditionModel = (Map<String, ConditionsModel>) request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_RECONDITION);
            if (reconditionModel != null && reconditionModel.containsKey(servletPath)) {
                conditionsModel = reconditionModel.get(servletPath);
            }
        }
        Map<String, Condition> conditionMap = conditionsModel.getConditions();
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

        Paginator paginator = conditionsModel.getPaginator();
        if (paginator.isNotPaginated()) {
            //判断是否为两层路径，若为两层则自动进行设置分页
            String servletPath = request.getServletPath();
            if (servletPath.matches("^/\\w+/\\w+/?$")) {
                paginator.setPage(0);
            } else {
                paginator = Paginator.NOT_PAGINATED;
            }
        }
        conditionContext.setPaginator(paginator);

        if (!paginator.isNotPaginated() || lastConditions.size() > 0) {
            Map<String, ConditionsModel> reconditionModel = (Map<String, ConditionsModel>) request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_RECONDITION);
            if (reconditionModel != null) {
                reconditionModel.clear();
            } else {
                reconditionModel = new HashMap<String, ConditionsModel>();
            }
            reconditionModel.put(request.getServletPath(), conditionsModel);
            request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_RECONDITION, reconditionModel);
        }

        conditionContextManager.setConditionContext(conditionContext);

        //保存条件模型
        model.addAttribute(MODEL_ATTRIBUTE_NAME_CONDITIONS, conditionsModel);
    }

    /**
     * 屏蔽分页
     */
    protected void concealPaginate() {
        conditionContextManager.getConditionContext().concealPaginate();
    }

    /**
     * 根据code获取国际化资源消息
     *
     * @param code 国际化消息编码
     * @return 国际化消息
     */
    protected String getMessage(String code) {
        return getMessage(code, null, null);
    }

    protected String getMessage(String code, String[] args, Locale locale) {
        ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
        return applicationContext.getMessage(code, args, locale);
    }

    /**
     * 添加Model消息
     *
     * @param model
     * @param type     消息类型：info、success、warning、danger
     * @param messages
     */
    private void addMessage(Model model, String type, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute("message", sb.toString());
        model.addAttribute("messageType", type);
    }

    /**
     * 添加成功Model消息
     *
     * @param model
     * @param messages
     */
    protected void addSuccessMessage(Model model, String... messages) {
        addMessage(model, "success", messages);
    }

    /**
     * 添加失败model消息
     *
     * @param model
     * @param messages
     */
    protected void addErrorMessage(Model model, String... messages) {
        addMessage(model, "danger", messages);
    }

    /**
     * 添加成功Flash消息
     *
     * @param redirectAttributes
     * @param messages
     */
    protected void addSuccessMessage(RedirectAttributes redirectAttributes, String... messages) {
        addMessage(redirectAttributes, "success", messages);
    }

    /**
     * 添加Flash消息
     *
     * @param redirectAttributes
     * @param type
     * @param messages
     */
    private void addMessage(RedirectAttributes redirectAttributes, String type, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
        redirectAttributes.addFlashAttribute("messageType", type);
    }

    /**
     * 添加错误Flash消息
     *
     * @param redirectAttributes
     * @param messages
     */
    protected void addErrorMessage(RedirectAttributes redirectAttributes, String... messages) {
        addMessage(redirectAttributes, "error", messages);
    }

}
