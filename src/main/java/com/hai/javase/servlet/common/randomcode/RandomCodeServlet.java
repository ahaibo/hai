/**
 * 
 */
package com.hai.javase.servlet.common.randomcode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Administrator
 * 
 */
public class RandomCodeServlet extends HttpServlet
{
	// 生成的随机码图片的宽度
	private int width = 80;
	// 生成的随机码图片的高度
	private int height = 22;
	
	private static final long serialVersionUID = -6579818248075824549L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		
		// 创建一个随机数生成器类。
		Random random = new Random();
		
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Times New Roman", Font.PLAIN, 20);
		// 设置字体。
		g.setFont(font);
		
		// 画边框。
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, width - 1, height - 1);
		
		// 随机产生50条干扰线，使图象中的认证码不易被其它程序探测到。
		int red = 0;
		int green = 0;
		int blue = 0;
		g.setColor(Color.GRAY);
		for (int i = 0; i < 50; i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(5);
			int yl = random.nextInt(5);
			g.drawLine(x, y, x + xl, y + yl);
		}
		
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		
		// 随机产生5位数字的验证码。
		for (int i = 0; i < 5; i++)
		{
			// 得到随机产生的验证码数字。
			
			int j = 0;
			StringBuffer sb = new StringBuffer();
			for (j = 0; j < 26; j++)
			{
				sb.append((char) (j + 'a'));
				sb.append((char) (j + 'A'));
			}
			for (j = 0; j < 10; j++)
			{
				sb.append((char) (j + '0'));
			}
			String strRand = String.valueOf(sb.toString().charAt(random.nextInt(62)));
			
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(150);
			green = random.nextInt(80);
			blue = random.nextInt(80);
			
			// 用随机产生的颜色将验证码绘制到图像中。
			//g.setColor(new Color(red, green, blue));
			g.drawString(strRand, 14 * i + 4, 17);
			
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		// 将四位数字的验证码保存到Session中。
		HttpSession session = request.getSession();
		session.setAttribute("randomCode", randomCode.toString());
		
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		response.setContentType("image/jpeg");
		
		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
}
