package pygman.invoice.invoice.quartz;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import pygman.invoice.invoice.goods.business.ebi.GoodsEbi;
import pygman.invoice.util.format.FormatUtil;

public class GoodsUseNumUpdateQuartz {
	private GoodsEbi goodsEbi;
	private JavaMailSenderImpl mailSender;
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	//spring定时完成指定的任务（作业）
	public void goodsUseNumUpdate(){
		goodsEbi.updateUseNum();
	}
	public void storeWarn(){
		//数据:商品名，超最大值，低最小值
		List<Object[]> temp = goodsEbi.getStoreWarnInfo();
		//消息发送:email
		//1.获取发送Email的对象MailSender
		//如果在spring环境中使用邮件发送
		//2.组织发送的数据：简单邮件，复杂邮件
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject("库存预警["+ FormatUtil.formatDateTime(System.currentTimeMillis())+"]");
		//要求与配置中使用的用户名相同
		msg.setFrom("itcast0228@126.com");
		//发送给对方
		msg.setTo("itcasthehe@126.com");
		
		StringBuilder sbf = new StringBuilder();
		
		sbf.append("【仓库预警信息】");
		for(Object[] objs:temp){
			String name = objs[0].toString();
			BigInteger max = (BigInteger)objs[1];
			if(max.toString().equals("1")){
				//超过最大值
				sbf.append("商品:[");
				sbf.append(name);
				sbf.append("]超过最大库存量，请停止补货!\r\n");
				continue;
			}
			BigInteger min = (BigInteger)objs[2];
			if(min.toString().equals("1")){
				//低于最低值
				sbf.append("商品:[");
				sbf.append(name);
				sbf.append("]小于最小库存量，请及时补货!\r\n");
			}
		}
		
		msg.setText(sbf.toString());
		
		//设置发送时间
		msg.setSentDate(new Date());
		//3.调用MailSender的send(发送邮件对象)
		mailSender.send(msg);
		System.out.println("end..........");
	}
}










