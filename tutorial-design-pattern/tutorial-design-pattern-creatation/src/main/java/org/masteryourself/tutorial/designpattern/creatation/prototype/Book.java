package org.masteryourself.tutorial.designpattern.creatation.prototype;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

import java.util.ArrayList;

/**
 * <p>description : Book
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 3:17 PM
 */
@Data
public class Book implements Cloneable {

    private String title;
    private Image image;

    @Override
    protected Book clone() throws CloneNotSupportedException {
        Book book = (Book) super.clone();
        // 深拷贝
        book.setImage((Image) this.image.clone());
        return book;
    }

}
