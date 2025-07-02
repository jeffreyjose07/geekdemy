package com.example.geektrust.model;

public enum Coupon {
    B4G1 {
        @Override
        public boolean isApplicable(Order order, float subTotal) {
            return order.getTotalQuantity() >= B4G1_MIN_PROGRAMS;
        }

        @Override
        public float discountAmount(Order order, float subTotal) {
            return order.getCheapestProgramPrice();
        }
    },
    DEAL_G20 {
        @Override
        public boolean isApplicable(Order order, float subTotal) {
            return order.hasCoupon(this) && subTotal >= DEAL_G20_THRESHOLD;
        }

        @Override
        public float discountAmount(Order order, float subTotal) {
            return subTotal * 20f / 100f;
        }
    },
    DEAL_G5 {
        @Override
        public boolean isApplicable(Order order, float subTotal) {
            return order.hasCoupon(this) && order.getTotalQuantity() >= DEAL_G5_MIN_PROGRAMS;
        }

        @Override
        public float discountAmount(Order order, float subTotal) {
            return subTotal * 5f / 100f;
        }
    },
    NONE {
        @Override
        public boolean isApplicable(Order order, float subTotal) {
            return false;
        }

        @Override
        public float discountAmount(Order order, float subTotal) {
            return 0f;
        }
    };

    private static final float DEAL_G20_THRESHOLD = 10000f;
    private static final int DEAL_G5_MIN_PROGRAMS = 2;
    private static final int B4G1_MIN_PROGRAMS = 4;

    public abstract boolean isApplicable(Order order, float subTotal);

    public abstract float discountAmount(Order order, float subTotal);
}
