select * FROM orquesta;

INSERT INTO orquesta VALUES (1, 'La Mejor', 'Mexico');

INSERT INTO orquesta VALUES (2, 'Fallen', 'USA');

INSERT INTO orquesta VALUES (3, 'Venz', 'Venezuela');

INSERT INTO orquesta VALUES (4, 'Colmena', 'Espana');



SELECT * FROM director;

INSERT INTO director VALUES (1, 'Jose Gonzalez', 'Mexico');

INSERT INTO director VALUES (2, 'John Myer', 'USA');

INSERT INTO director VALUES (3, 'Felipe Pelon', 'Venezuela');

INSERT INTO director VALUES (4, 'Maria Pedrastra', 'Espana');



SELECT * FROM conciertos;



INSERT INTO conciertos VALUES (1, 'Los Magicos', TO_DATE('15/01/2019 23:00:00', 'DD/MM/YYYY HH24:MI:SS'), TO_DATE('16/12/2012 05:00:00', 'DD/MM/YYYY HH24:MI:SS'), 1, 1, 1, 1, 60);

INSERT INTO conciertos VALUES (2, 'Los Perros', TO_DATE('20/02/2019 22:00:00', 'DD/MM/YYYY HH24:MI:SS'), TO_DATE('21/02/2012 04:00:00', 'DD/MM/YYYY HH24:MI:SS'), 2, 2, 2, 3, 6);




SELECT * FROM sala;


INSERT INTO sala VALUES (1, 'Altamira', '10000');


DELETE FROM sala;