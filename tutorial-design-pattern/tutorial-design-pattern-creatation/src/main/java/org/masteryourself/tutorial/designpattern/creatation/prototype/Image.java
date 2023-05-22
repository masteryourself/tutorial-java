package org.masteryourself.tutorial.designpattern.creatation.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>description : Image
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 3:21 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Cloneable {

    public String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
