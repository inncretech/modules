package com.inncretech.social;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.inncretech.social.service.impl.SocialServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationcontext-social.xml" })
public class DefaultSocialServiceImplTest {

  @Test
  public void test() {
    SocialServiceImpl ssl = new SocialServiceImpl();
    String URL = "https://www.google.co.in/search?q=saint+bernard+puppies&bav=on.2,or.r_cp.r_qf.&bvm=bv.48572450,d.bmk&biw=1024&bih=647&um=1&ie=UTF-8&hl=en&tbm=isch&source=og&sa=N&tab=wi&ei=p8rRUbffKc3irAeC74CgAQ#facrc=_&imgdii=_&imgrc=HP7DpAMLgp9fzM%3A%3BGN-xu55ubWtbYM%3Bhttp%253A%252F%252Fwww.keystopet.com%252Fwp-content%252Fuploads%252F2012%252F05%252FBuy-Saint-Bernard-Puppies1.jpg%3Bhttp%253A%252F%252Faurangabad.keystopet.com%252Fgallery%252Fsaint-bernard-puppies-for-sale%252F%3B900%3B929";
    MultiValueMap<String, String> postData = new LinkedMultiValueMap<String, String>();
    String accessToken = "CAACEdEose0cBAJPGQXwScM8SJsrHsiVotnQFa2Inx3XROQ9xkQyCc9F4CCuFiIjII0zlk9OC2JUrZC1aaPQ6HdZCeE696SU3kcGMkSr90YIhR5qrEJl2OwajMcypbQ2K1k0p6sZCzAsKNZCeZCKUwwgDODEwZCqtAZD";
    postData.add("link", "http://www.saintbernard.com/");
    postData.add("name", "kavita");
    postData.add("picture", URL);
    postData.add("description", "This is a sample description !");
    postData.add("message", "This is a Saint Bernard");
    ssl.sharePostToFacebook("100006242351368", accessToken, postData);
    // sss.shareSourceViaFacebook( uc,"This is a Saint Bernard",
    // "http://www.saintbernard.com/", "Gaurav", "This is my doggy !", URL);
  }
}