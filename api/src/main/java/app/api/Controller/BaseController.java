package app.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public abstract  class BaseController {
    private final MessageSource msg;

    @Autowired
    public BaseController(MessageSource msg) {

        this.msg = msg;
    }


    protected String createResponseMsg(String message)
    {
        return msg.getMessage(message,null, LocaleContextHolder.getLocale());

    }
}
