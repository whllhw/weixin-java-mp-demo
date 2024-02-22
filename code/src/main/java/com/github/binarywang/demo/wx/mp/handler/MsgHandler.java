package com.github.binarywang.demo.wx.mp.handler;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import com.github.binarywang.demo.wx.mp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {
        String content = "";
        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            content = String.format("已收到您于%s发送的消息~", dateFormat.format(new Date(wxMessage.getCreateTime() * 1000)));
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        // try {
        //     if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
        //         && weixinService.getKefuService().kfOnlineList()
        //         .getKfOnlineList().size() > 0) {
        //         return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
        //             .fromUser(wxMessage.getToUser())
        //             .toUser(wxMessage.getFromUser()).build();
        //     }
        // } catch (WxErrorException e) {
        //     e.printStackTrace();
        // }

        //TODO 组装回复消息
        this.logger.info("[RECV] user:{} content:{}", wxMessage.getFromUser(), wxMessage.getContent());
        this.logger.info("收到信息内容：" + JsonUtils.toJson(wxMessage));

        return new TextBuilder().build(content, wxMessage, weixinService);

    }

}
