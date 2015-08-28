insert into items(id,name,description,price,imagePath,deleted) values(1,'パソコン','最新パーソナルコンピューター',50000,'pc.jpg',false);
insert into items(id,name,description,price,imagePath,deleted) values(2,'マウス','光学式マウス',500,'mouse.jpg',false);

insert into admin_users(id,name,email,password) values(1,'大迫','kenji@osako','d349d3e6f620a8c84e2b14092cb1c2b4636be3aa0bc52b12119bb561b7855ff5f6902f15deb7cb49');--pass:12345678
insert into admin_users(id,name,email,password) values(2,'田中','taro@tanaka','2f087a5b020b78b67b86d82fa1a62744b37c251e9bcc7b5a944e03f4aa4d7cc6d66f8ec31413a021');--pass:56789123
insert into admin_users(id,name,email,password) values(3,'ラクス','rakus@rakus','9c95602d36f9de9f702b4be4928bd7b9bdbfbcbf7f211862116aaf0fc17f2c9e9cb6f894d9d029a6');--pass:rakusrakus

insert into users(id,name,email,password,address,telephone) values(1,'利用者A','syuzo@matuoka','1234','東京都渋谷区1-1-1','0120-443-443');
insert into users(id,name,email,password,address,telephone) values(2,'利用者B','taro@tanaka','5678','神奈川県横浜市1-1-2','01-2345-6789');
