select * from onboarding.client c
inner join onboarding.phone p
on p.client_phone_id = c.id
inner join onboarding.address a
on a.id = c.client_adress_id
inner join onboarding.client_type ct
on ct.id = c.client_type_id
where
c.id = 1;