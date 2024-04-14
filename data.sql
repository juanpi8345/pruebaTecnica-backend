USE consultorio_medico;

INSERT INTO consulting_rooms VALUES(
	("1"),
    ("2"),
    ("3")
);

INSERT INTO specialities VALUES (
	("Clinica medica"),
    ("Dermatologia"),
    ("Pediatria"),
    ("Cariologia")
);

INSERT INTO professionals (professional_dni,end,lastname,name,start) VALUES(
	("30500000",localtime(2200),"Perez","Leandro",Localtime(0900)),
    ("22505230",localtime(2000),"Hernandez","Juan",Localtime(1000)),
    ("15700200",localtime(2200),"Perez","Karina",Localtime(0900)),
    ("17730200",localtime(2000),"Fernandez","Oscar",Localtime(0800)),
    ("25530000",localtime(2300),"Pirez","Luciana",Localtime(1100)),
    ("23000000",localtime(2000),"Ariaga","Juan",Localtime(1000)),
    ("35300000",localtime(2300),"Gimenez","Jose",Localtime(1100)),
    ("30520000",localtime(2300),"Ortiz","Marcela",Localtime(1000)),
	("17730200",localtime(2300),"Martinez","Julian",Localtime(0800)),
    ("34560000",localtime(2100),"Ruch","Martin",Localtime(0800)),
    ("21530201",localtime(1900),"Fernandez","Lucia",Localtime(1100))
);

INSERT INTO professionals_specialities (professional_dni, speciality_name) VALUES(
	("30500000","Clinica medica"),
    ("21530201","Clinica medica"),
    ("34560000","Clinica medica"),
    ("25530000","Clinica medica"),
    ("23000000","Dermatologia"),
	("30520000","Dermatologia"),
	("17730200","Dermatologia"),
	("34560000","Dermatologia"),
    ("30520000","Pedriatria"),
    ("35300000","Pedriatria"),
    ("17730200","Pedriatria"),
    ("15700200","Pedriatria"),
    ("30500000","Cardiologia"),
    ("25530000","Cardiologia"),
    ("30520000","Cardiologia"),
    ("21530201","Cardiologia")
);

INSERT INTO patients (patient_dni,lastname,name) VALUES(
	("45199461","Pregliasco","Juan Pablo"),
    ("47200100","Fernandez","Joaquin"),
    ("39200400","Benitez","Lara"),
    ("40000000","Martinez","Sofia")
)