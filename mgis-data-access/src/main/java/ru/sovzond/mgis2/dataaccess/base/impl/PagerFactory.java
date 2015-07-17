package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;

/**
 * Created by Alexander Arakelyan on 15.07.15.
 */
public class PagerFactory {
    public static <T> PageableBase<T> createDefault(String orderBy, int first, int max) {
        return new DefaultPager<>(orderBy, first, max);
    }

    public static <T> PageableBase<T> createDefault(int first, int max) {
        return new DefaultPager<>(first, max);
    }

    private static class DefaultPager<T> extends PageableBase<T> {

        private DefaultPager(int first, int max) {
            this.first = first;
            this.max = max;
        }

        private DefaultPager(String orderBy, int first, int max) {
            this.orderBy = prepareOrderBy(orderBy);
            this.first = first;
            this.max = max;
        }

        @Override
        protected void applyFilter(Criteria criteria) {

        }
    }
}