package qnu.cntt.dacky.service;

import org.springframework.scheduling.annotation.Async;

import qnu.cntt.dacky.domain.Account;

public interface MailService {
	@Async
	public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);
	@Async
	public void sendEmailFromTemplate(Account user, String templateName, String titleKey);
	@Async
	public void sendActivationEmail(Account user);
	@Async
	public void sendCreationEmail(Account user);
	@Async
	public void sendPasswordResetMail(Account user);
}
