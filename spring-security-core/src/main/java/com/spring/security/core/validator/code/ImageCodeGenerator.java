package com.spring.security.core.validator.code;

import com.spring.security.core.commons.Conts;
import com.spring.security.core.commons.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author ：miaoqs
 * @date ：2019-06-10 21:08
 * @description：注明组件名后autowird的时候调用同名的组件
 */
@Component("imageCodeGenerator")
@Slf4j
public class ImageCodeGenerator implements ValidateCodeGenerator{

	@Autowired
    @Getter
    @Setter
	private SecurityProperties securityProperties;
	
	@Override
	public ImageCode generate(ServletWebRequest request) {
		return createImageCode(request.getRequest());
	}
	
	private ImageCode createImageCode(HttpServletRequest request){
        int width = securityProperties.getCode().getImage().getWidth();
        int height = securityProperties.getCode().getImage().getHeight();
        int expireIn = securityProperties.getCode().getImage().getExpireIn();
        log.info("===========生成的验证码的宽度：{},===========生成的验证码的高度：{}, ===========生成的验证码的过期时间：{}", width, height, expireIn);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        /**
         * 验证码随机数字
         */
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            List<String> list = Arrays.asList(Conts.IMAGE_CODE.split(","));
            int nextInt = random.nextInt(list.size());
            String rand = list.get(nextInt);
            if (nextInt % 2 == 0){
                rand.toUpperCase();
            }
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, expireIn);
    }
	
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
