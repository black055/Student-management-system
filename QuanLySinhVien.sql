/***** Create table *****/
Create database QuanLySinhVien;
Use QuanLySinhVien;

/***** Create table *****/
Create table SinhVien (
	maSV nvarchar(8),
	tenSV nvarchar(30),
	gioiTinh nvarchar(10),
	cmnd nvarchar(12),
	maLop int,
    primary key (maSV)
);

Create table Lop (
	tenLop nvarchar(10),
	maLop int,
    primary key(maLop)
);

Create table MonHoc (
	tenMH nvarchar(45),
	maLop int,
	maMH nvarchar(10),
	phongHoc nvarchar(10),
    primary key(maMH)
);

Create table dkmh (
	maMH nvarchar(10),
	maSV nvarchar(8),
	diemGK float,
	diemCK float,
	diemKhac float,
	diemTong float,
    primary key (maMH, maSV)
);

Create table TaiKhoan (
	maTK int,
	tenTK nvarchar(15),
	matKhau nvarchar(15),
	GiaoVu bit,
    primary key (maTK)
);

/***** Create Foreign Key *****/
Alter table SinhVien add constraint FK_SinhVien_Lop foreign key (maLop)
references Lop (maLop);

Alter table MonHoc add constraint FK_MonHoc_Lop foreign key (maLop)
references Lop (maLop);

Alter table dkmh add constraint FK_dkmh_MonHoc foreign key (maMH)
references MonHoc (maMH);

Alter table dkmh add constraint FK_dkmh_SinhVien foreign key (maSV)
references SinhVien (maSV);