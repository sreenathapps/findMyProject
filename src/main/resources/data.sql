INSERT INTO PROJECT(ID, NAME, BUDGET) VALUES
    (1, 'Project Alpha', 50000.00),
    (2, 'Project Beta', 100000.00),
    (3, 'Project Gamma', 150000.00),
    (4, 'Project Delta', 75000.00);

INSERT INTO RESEARCHER(ID, NAME, SPECIALIZATION) VALUES
    (1, 'Marie Curie', 'Radioactivity'),
    (2, 'Albert Einstein', 'Relativity'),
    (3, 'Isaac Newton', 'Classical Mechanics'),
    (4, 'Neils Bohr', 'Quantum Mechanics');

INSERT INTO RESEARCHER_PROJECT (PROJECTID, RESEARCHERID) VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3),
    (3, 4),
    (4, 4);