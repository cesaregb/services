Select sc.* from ServiceCategory as sc, ServiceType as st
  inner join (
    Select * From OrderType as ot
      inner join ServiceType_has_OrderType as cnT on cnT.OrderType_idOrderType = ot.idOrderType
      where ot.idOrderType=1
    ) as result on result.ServiceType_idServiceType=st.idServiceType
where st.idServiceCategory=sc.idServiceCategory group by idServiceCategory;
