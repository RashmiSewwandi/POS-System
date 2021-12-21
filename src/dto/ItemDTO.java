package dto;

import java.io.Serializable;
import java.util.Objects;

public class ItemDTO implements Serializable {

        private String code;
        private String description;
        private String packSize;
        private Double unitPrize;
        private int QtyOnHand;

        public ItemDTO(String code, String description, String packSize, Double unitPrize, int qtyOnHand) {
            this.setCode(code);
            this.setDescription(description);
            this.setPackSize(packSize);
            this.setUnitPrize(unitPrize);
            setQtyOnHand(qtyOnHand);
        }



        public ItemDTO() {
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
            return "Item{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    ", packSize='" + packSize + '\'' +
                    ", unitPrize=" + unitPrize +
                    ", QtyOnHand=" + QtyOnHand +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ItemDTO item = (ItemDTO) o;
            return Objects.equals(code, item.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }

}





