create table projects(name varchar(255) NOT NULL, description varchar(255) NOT NULL, project_id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(project_id));
create table meetings(year varchar(255) NOT NULL, meeting_id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(meeting_id), FOREIGN KEY(project_id) REFERENCES projects(project_id));

