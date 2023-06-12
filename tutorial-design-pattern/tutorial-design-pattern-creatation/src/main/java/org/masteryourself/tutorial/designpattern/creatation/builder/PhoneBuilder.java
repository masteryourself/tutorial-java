package org.masteryourself.tutorial.designpattern.creatation.builder;

/**
 * <p>description : PhoneBuilder
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 6:39 PM
 */
public interface PhoneBuilder {

    PhoneBuilder cpu(String cpu);

    PhoneBuilder mem(String mem);

    PhoneBuilder disk(String disk);

    Phone build();

    /**
     * 产品角色（Product）：Phone
     * 抽象建造者（Builder）：PhoneBuilder
     * 具体建造者(Concrete Builder）：XiaomiPhoneBuilder
     */
    class XiaomiPhoneBuilder implements PhoneBuilder {

        private Phone phone;

        public XiaomiPhoneBuilder() {
            this.phone = new Phone();
        }

        @Override
        public PhoneBuilder cpu(String cpu) {
            this.phone.setCpu(cpu);
            return this;
        }

        @Override
        public PhoneBuilder mem(String mem) {
            this.phone.setMem(mem);
            return this;
        }

        @Override
        public PhoneBuilder disk(String disk) {
            this.phone.setDisk(disk);
            return this;
        }

        @Override
        public Phone build() {
            return this.phone;
        }
    }

}
