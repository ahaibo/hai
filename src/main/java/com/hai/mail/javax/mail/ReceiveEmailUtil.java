package com.hai.mail.javax.mail;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.pop3.POP3Folder;
import com.hai.mail.MessageInfo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/10/8.
 */
public class ReceiveEmailUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List<MessageInfo> searchEmailWithPop3(String host, String name, String pass, String flag) throws MessagingException {

        Store store = connectionWithPop3(host, name, pass);

        POP3Folder folder = (POP3Folder) store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        FetchProfile profile = new FetchProfile();

        //获取邮件的UID
        profile.add(UIDFolder.FetchProfileItem.UID);
        profile.add(FetchProfile.Item.ENVELOPE);

        String msgId = queryIsRead(name);
        Message[] messages = null;
        if (!"".equals(msgId)) {
            String[] readIds = msgId.split(",");
            boolean isRead = !"0".equals(flag);
            messages = folder.search(new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    boolean match = false;
                    try {
                        String uid = folder.getUID(message);
                        boolean read = Arrays.binarySearch(readIds, uid) >= 0;
                        match = isRead ? read : !read;
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return match;
                }
            });
        } else {
            if ("0".equals(flag)) {
                //未读，获取所有邮件
                messages = folder.getMessages();
                folder.fetch(messages, profile);
            }
        }
        folder.close(true);
        close(store);

        return convertToMessageInfo(messages);
    }

    public static List<MessageInfo> searchEmailWithImap(String host, String name, String pass) {
        List<MessageInfo> msgs = new ArrayList<>();
        Store store = connectionWithImap(host, name, pass);
        try {
            IMAPFolder folder = (IMAPFolder) store.getFolder("inbox");//获取inbox邮件
            folder.open(Folder.READ_WRITE);//打开邮件夹
            Message[] messages = folder.getMessages();
            for (Message msg : messages) {
                MessageInfo info = newInstance(msg);
                info.setUid(folder.getUID(msg));
                info.setRead(msg.isSet(Flags.Flag.SEEN));
                msgs.add(info);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return msgs;
    }

    public static Store connectionWithPop3(String host, String name, String pass) {
        String protocol = "pop3";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.store.protocol", protocol);
        properties.setProperty("mail.smtp.host", host);
        return connection(host, name, pass, properties);
    }

    public static Store connectionWithImap(String host, String name, String pass) {
        String protocol = "imap";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.store.protocol", protocol);
        properties.setProperty("mail.imap.host", host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.imap.class", IMAPStore.class.getName());
        return connection(host, name, pass, properties);
    }

    public static Store connection(String host, String name, String pass, Properties properties) {
        String protocol = properties.getProperty("mail.store.protocol");
        // 获取默认的 Session 对象。
        Session session = Session.getDefaultInstance(properties, null);
        Store store = null;
        try {
            store = session.getStore(protocol);
            store.connect(host, name, pass);
        } catch (MessagingException e) {
            e.printStackTrace();
            close(store, true);
        }
        return store;
    }

    public static void close(Store store) {
        close(store, false);
    }

    public static void close(Store store, boolean set2null) {
        if (null != store) {
            if (store.isConnected()) {
                try {
                    store.close();
                } catch (MessagingException e1) {
                    e1.printStackTrace();
                }
            } else {
                if (set2null) {
                    store = null;
                }
            }
        }
    }

    public static MessageInfo newInstance(Message message) {
        MessageInfo info = null;
        if (null != message) {
            info = new MessageInfo();
            try {
                info.setSubject(message.getSubject());
                info.setFrom(getFromAddress(message.getFrom()));
                info.setTo(getFromAddress(message.getRecipients(Message.RecipientType.TO)));
                info.setCc(getFromAddress(message.getRecipients(Message.RecipientType.CC)));
                info.setBcc(getFromAddress(message.getRecipients(Message.RecipientType.BCC)));
                info.setSendDate(format.format(message.getSentDate()));
                info.setReceiveDate(format.format(message.getReceivedDate()));
                info.setContent(getMessageContent(message));
                info.setSize(message.getSize());
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return info;
    }

    public static String getFromAddress(Address[] addresses) throws MessagingException {
        if (null == addresses || addresses.length == 0) {
            return "";
        }

        StringBuilder from = new StringBuilder();
        boolean isFirst = true;
        for (Address address : addresses) {
            if (!isFirst) {
                from.append(",");
            }
            from.append(((InternetAddress) address).getAddress());
            isFirst = false;
        }

        return from.toString();
    }

    public static String getMessageContent(Message message) throws IOException, MessagingException {

        Object content = message.getContent();
        String type = message.getContentType();

        if (type.startsWith("text/plain")) {
            return content.toString();
        }

        if (type.startsWith("multipart")) {
            Multipart part = (Multipart) content;
            BodyPart body = part.getBodyPart(0);

            int len, size = 1024;
            char[] buffer = new char[size];
            StringBuilder text = new StringBuilder(size);
            InputStreamReader reader = new InputStreamReader(body.getInputStream());
            while ((len = reader.read(buffer)) != -1) {
                text.append(buffer, 0, len);
            }

//            text.trimToSize();
            return text.toString();
        }

        return "";
    }

    //TODO
    private static List<MessageInfo> convertToMessageInfo(Message[] messages) {
        return null;
    }

    //TODO
    private static String queryIsRead(String name) {
        return "";
    }

}
