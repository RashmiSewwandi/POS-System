package views.tm;

public class CartTM {
        private String code;
        private String description;
        private int qty;
        private double unitPrice;
        private double total;

        public CartTM() {
        }

        public CartTM(String code, String description, double unitPrice, int qty, double total) {
            this.code = code;
            this.description = description;
            this.unitPrice = unitPrice;
            this.qty = qty;
            this.total = total;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }


        // details print kara ganna
        @Override
        public String toString() {
            return "CartTM{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    ", unitPrice=" + unitPrice +
                    ", qty=" + qty +
                    ", total=" + total +
                    '}';
        }

}
