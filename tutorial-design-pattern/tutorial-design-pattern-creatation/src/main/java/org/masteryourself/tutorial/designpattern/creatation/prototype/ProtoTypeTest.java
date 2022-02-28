package org.masteryourself.tutorial.designpattern.creatation.prototype;

/**
 * <p>description : ProtoTypeTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 3:23 PM
 */
public class ProtoTypeTest {

    public static void main(String[] args) throws Exception{
        Book book1 = new Book();
        book1.setTitle("《三体》");
        book1.setImage(new Image("图片1"));
        // 以原型方式拷貝一份
        Book book2 = book1.clone();
        book2.getImage().setName("图片2");
        System.out.println(book1);
        System.out.println(book2);
    }

}
