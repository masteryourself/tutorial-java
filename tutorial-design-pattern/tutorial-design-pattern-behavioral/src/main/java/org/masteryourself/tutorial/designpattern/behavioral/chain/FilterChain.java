package org.masteryourself.tutorial.designpattern.behavioral.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : FilterChain
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 8:34 PM
 */
public class FilterChain implements Filter {

    private final List<Filter> filterList = new ArrayList<>();
    private final MethodHandler handler;
    private int cursor = 0;

    public FilterChain(MethodHandler handler) {
        this.handler = handler;
    }

    public void addFilter(Filter filter) {
        filterList.add(filter);
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if (cursor >= filterList.size()) {
            handler.method();
            return;
        }
        filterList.get(cursor++).doFilter(request, response, chain);
    }
}
