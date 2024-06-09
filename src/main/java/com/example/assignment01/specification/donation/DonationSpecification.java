package com.example.assignment01.specification.donation;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.utils.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class DonationSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Donation> buildWhere(String search) {

        Specification<Donation> where = null;

        if (!StringUtils.isEmpty(search)) {

            search = Utils.formatSearch(search);

            CustomSpecification status = new CustomSpecification("status", search);
            where = Specification.where(status);
        }
        return where;
    }
}

    @SuppressWarnings("serial")
    @RequiredArgsConstructor
    class CustomSpecification implements Specification<Donation> {

        @NonNull
        private String field;
        @NonNull
        private Object value;

        @Override
        public Predicate toPredicate(Root<Donation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            if (field.equalsIgnoreCase("status")) {
                //Chuyển đổi giá trị value sang chuỗi.
                String  str = value.toString();
                // Chia chuỗi thành một mảng các ký tự, sử dụng phương thức split().
                String[] arr = str.split("");
                // Lấy giá trị của biểu thức status từ gốc root.
                Expression<String> pa = root.get("status");
                // Kiểm tra xem giá trị của biểu thức status có nằm trong mảng arr không. Nếu có, thì trả về true, ngược lại trả về false.
                return pa.in(arr );
            }
            return null;
        }
    }

