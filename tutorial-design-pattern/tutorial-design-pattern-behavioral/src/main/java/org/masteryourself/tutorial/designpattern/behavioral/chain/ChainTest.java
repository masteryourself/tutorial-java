package org.masteryourself.tutorial.designpattern.behavioral.chain;

/**
 * <p>description : ChainTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 8:37 PM
 */
public class ChainTest {

    public static void main(String[] args) {
        FilterChain chain = new FilterChain(new MethodHandler());
        chain.addFilter(new Filter.LoggingFilter());
        chain.addFilter(new Filter.EncodingFilter());
        chain.addFilter(new Filter.ResponseFilter());
        chain.doFilter(new Request(), new Response(), chain);
    }

}
