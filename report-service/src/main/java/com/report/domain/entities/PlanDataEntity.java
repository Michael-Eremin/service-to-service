package com.report.domain.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="luk_plan_data", schema="report")
@NoArgsConstructor
@Getter
@Setter

public class PlanDataEntity {
    @Id
    private Long id;

    @Column(name = "variant_id")
    private Integer variantId;

    @Column(name = "variant")
    private String variant;

    @Column(name = "product")
    private String product;

    @Column(name = "bud_prod_class")
    private String budProdClass;

    @Column(name = "group_channel")
    private String groupChannel;

    @Column(name = "plant")
    private String plant;

    @Column(name = "contragent")
    private String contragent;

    @Column(name = "contract")
    private String contract;

    @Column(name = "target")
    private String target;

    @Column(name = "transport")
    private String transport;

    @Column(name = "plan")
    private Integer plan;

    @Column(name = "request")
    private Integer request;

    @Column(name = "+/- plan/request")
    private Integer planRequest;

    @Column(name = "prod_ksss_id")
    private Integer prodKsssId;

    @Column(name = "contragent_ksss_id")
    private Integer contragentKsssId;

    @Column(name = "is_exp")
    private Integer isExp;

    @Column(name = "price")
    private Double price;

    @Column(name = "ex_request")
    private Integer exRequest;

    @Column(name = "inc2_1")
    private String inc21;

    @Column(name = "basis")
    private String basis;

    @Column(name = "obl_name")
    private String oblName;

    @Column(name = "row_id")
    private Integer rowId;

    @Column(name = "program")
    private String program;

    @Column(name = "price2")
    private Double price2;

    @Column(name = "price3")
    private Double price3;

    @Column(name = "depot")
    private String depot;
}
