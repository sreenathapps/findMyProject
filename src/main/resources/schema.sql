DROP TABLE IF EXISTS RESEARCHER_PROJECT;
DROP TABLE IF EXISTS PROJECT;
DROP TABLE IF EXISTS RESEARCHER;

CREATE TABLE PROJECT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME TEXT,
    BUDGET DOUBLE
);

CREATE TABLE RESEARCHER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME TEXT,
    SPECIALIZATION TEXT
);

CREATE TABLE RESEARCHER_PROJECT(
    PROJECTID INTEGER,
    RESEARCHERID INTEGER,
    PRIMARY KEY(PROJECTID, RESEARCHERID),
    FOREIGN KEY (PROJECTID) REFERENCES PROJECT(ID),
    FOREIGN KEY (RESEARCHERID) REFERENCES RESEARCHER(ID)
);