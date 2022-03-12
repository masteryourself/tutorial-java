package org.masteryourself.tutorial.designpattern.behavioral.chain;

/**
 * <p>description : Filter
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 8:32 PM
 */
public interface Filter {

    void doFilter(Request request, Response response, FilterChain chain);

    class LoggingFilter implements Filter {

        @Override
        public void doFilter(Request request, Response response, FilterChain chain) {
            System.out.println("LoggingFilter 执行之前");
            chain.doFilter(request, response, chain);
            System.out.println("LoggingFilter 执行之后");
        }
    }

    class EncodingFilter implements Filter {

        @Override
        public void doFilter(Request request, Response response, FilterChain chain) {
            System.out.println("EncodingFilter 执行之前");
            chain.doFilter(request, response, chain);
            System.out.println("EncodingFilter 执行之后");
        }
    }

    class ResponseFilter implements Filter {

        @Override
        public void doFilter(Request request, Response response, FilterChain chain) {
            System.out.println("ResponseFilter 执行之前");
            chain.doFilter(request, response, chain);
            System.out.println("ResponseFilter 执行之后");
        }
    }

}
