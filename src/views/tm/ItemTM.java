package views.tm;

public class ItemTM {
        private String code;
        private String description;
        private String packSize;
        private Double unitPrize;
        private int QtyOnHand;

        public ItemTM(String code, String description, String packSize, Double unitPrize, int qtyOnHand) {
            this.setCode(code);
            this.setDescription(description);
            this.setPackSize(packSize);
            this.setUnitPrize(unitPrize);
            setQtyOnHand(qtyOnHand);
        }



        public ItemTM() {
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

        public String getPackSize() {
            return packSize;
        }

        public void setPackSize(String packSize) {
            this.packSize = packSize;
        }

        public Double getUnitPrize() {
            return unitPrize;
        }

        public void setUnitPrize(Double unitPrize) {
            this.unitPrize = unitPrize;
        }

        public int getQtyOnHand() {
            return QtyOnHand;
        }

        public void setQtyOnHand(int qtyOnHand) {
            QtyOnHand = qtyOnHand;
        }

        @Override
        public String toString() {
            return "ItemTM{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    ", packSize='" + packSize + '\'' +
                    ", unitPrize=" + unitPrize +
                    ", QtyOnHand=" + QtyOnHand +
                    '}';
        }

    }




