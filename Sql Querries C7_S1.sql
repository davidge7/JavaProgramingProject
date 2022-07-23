create database if not exists jukebox;

use jukebox;

create table customer ( customerid int primary key auto_increment , customername varchar(50),customerlocation varchar(50),
 customeremail varchar(100) unique, customerpassword varchar(20));
 
 insert into customer values(null,'David','xyz','david@gmail.com','david1234');
 insert into customer values(null,'Blessy','xyz','blessy@gmail.com','blessy1234');
 

create table songartist ( songartistid int primary key not null,songartistname varchar(30) not null ,songartistcity varchar(20),
 songartistcontry varchar(20), songartistYob year);

insert into songartist values(1,'Imagine Dragons','','USA','2017');
insert into songartist values(2,'The Weeknd','Toranto','Canada','1990');
insert into songartist values(3,'The ChainSmokers',null,'USA','2014');
insert into songartist values(4,'Tonnes And I','','Australia','2000');
insert into songartist values(5,'Drake','','Canadian','1986');

create table songalbum ( songalbumid int primary key not null , songalbumname varchar(30) not null, songalbumproducername varchar(30) ,
songalbumstudioname varchar(30));

insert into songalbum values(1,'Evolve','Matman&Robin','InterScope Records');
insert into songalbum values(2,'After Hours','Max Martin','XO Records');
insert into songalbum values(3,'JOY Division','Martin Hannet','Britannia Row Studios');
insert into songalbum values(4,'Single Song','Konstantin Kersting','');
insert into songalbum values(5,'Views','Noah Shebib','Republic Records');


create table song( songid int primary key not null , songartistid int , songgenere varchar(20) , songtitle varchar(50),songduration time,
songalbumid int,songreleaseyear year,songlink varchar(200),
constraint sar foreign key(songartistid) references songartist(songartistid),
constraint sab foreign key(songalbumid) references songalbum(songalbumid));

insert into song values (1,1,'Pop Rock','Beliver','00:03:24',1,2013,'D:\\SE\\Jukebox PROJECT\\content\\songs project\\Believer.wav');
insert into song values (2,2,'R&B','Blinding Lights','00:03:23',2,2019,'D:\\SE\\Jukebox PROJECT\\content\\songs project\\Blinding Lights.wav');
insert into song values (3,3,'EDM','Closer','00:04:03',3,2016,'D:\\SE\\Jukebox PROJECT\\content\\songs project\\Closer.wav');
insert into song values (4,4,'Electro pop','Dance Monkey','00:03:29',4,2019,'D:\\SE\\Jukebox PROJECT\\content\\songs project\\Dance Monkey.wav');
insert into song values (5,5,'Afro Beat','One Dance','00:02:54',5,2016,'D:\\SE\\Jukebox PROJECT\\content\\songs project\\One Dance.wav');

create table podcastspeaker ( podcastspeakerid int primary key, podcastspeakername varchar(30),podcastspeakercity varchar (30),
 podcastspeakercontry varchar (30), podcastspeakerYOB year ,podcastspeakerocupation varchar(100));
 
insert into podcastspeaker values(1,'Kalki Koechlin', 'Pondicherry','India','1984','Actress');
insert into podcastspeaker values(2,'Arun chahal','jind','India','1990','Wellness Coach');
insert into podcastspeaker values(3,'Tim ferris','New York','USA','1977','Investor');
insert into podcastspeaker values(4,'Brene Brown','San Antanio','USA','1965','Research Professor');   
insert into podcastspeaker values(5,'Joe Rogan','New Jersy','USA','1967','Actor');


create table podcastseries ( podcastseriesid int primary key,podcastseriesname varchar(50),podcastseriesproducer varchar(50),
podcastseriesstudioname varchar(30));

insert into podcastseries values(1,'Indias young','BBC World','Home Studio');
insert into podcastseries values(2,'The Pursuit Of Wellness','MoveFit','Chefingo');
insert into podcastseries values(3,'Into your Garden','TruBasket','AgroCult');
insert into podcastseries values(4,'','','');
insert into podcastseries values(5,'','','');

create table podcast ( podcastid int primary key ,podcastspeakerid int ,podcasttitle varchar(50), podcasttopic varchar(50),
 podcastduration time,podcastseriesid int ,podcastreleasedate date, podcastlink varchar(200),
constraint psp foreign key(podcastspeakerid) references podcastspeaker(podcastspeakerid),
constraint pse foreign key(podcastseriesid) references podcastseries(podcastseriesid));

insert into podcast values(1,1,'Being Fit','Well Being','00:03:55',1,'2020-05-12','D:\\SE\\Jukebox PROJECT\\content\\podcasts projects\\Being Fit.wav');
insert into podcast values(2,2,'Setting Right Goals','Fitness','00:03:10',2,'2018-09-14','D:\\SE\\Jukebox PROJECT\\content\\podcasts projects\\Harnessing Love.wav');
insert into podcast values(3,3,'Life Around Garden','Gardening','00:02:41',3,'2017-06-03','D:\\SE\\Jukebox PROJECT\\content\\podcasts projects\\Life Around Garden.wav');
insert into podcast values(4,4,'Love All','Life Style','00:03:01',4,'2019-12-24','D:\\SE\\Jukebox PROJECT\\content\\podcasts projects\\Love All.wav');
insert into podcast values(5,5,'Thriving in Life','Life Style','00:03:38',5,'2020-11-16','D:\\SE\\Jukebox PROJECT\\content\\podcasts projects\\Thriving in life.wav');


create table playlist( playlistid int primary key auto_increment ,playlistname varchar (50), customerid int ,
constraint pcust foreign key(customerid) references customer ( customerid));

insert into playlist values(null,'happy',1);
insert into playlist values(null,'love',1);
insert into playlist values(null,'fitness',1);

create table songplaylist (songplaylistid int primary key auto_increment, playlistid int ,songid int,
constraint splplid foreign key( playlistid) references playlist( playlistid),
constraint splsngid foreign key(songid) references song( songid));

insert into songplaylist values(null,1,4);
insert into songplaylist values(null,1,5);

create table podcastplaylist(podcastplaylistid int primary key auto_increment, playlistid int , podcastid int , 
constraint pplplid foreign key( playlistid) references playlist( playlistid),
constraint pplpcid foreign key (podcastid) references podcast ( podcastid));

insert into podcastplaylist values(null,3,1);
insert into podcastplaylist values(null,3,2);

-------------------------------------------------------------------------------------------------------------------------
## jb process querries

select s.songlink from song s where s.songid=2;

select p.podcastlink from podcast p where p.podcastid=2;

insert into customer values(null,'"+name+"','"+location+"','"+email+"','"+password+"');

insert into playlist values(null,'"+playlistname+"',"+cid+");

insert into playlist values(null,'"+playlistname+"',"+cid+");

select p.playlistid, p.playlistname from playlist p where p.customerid = "+cid";

select p.playlistname from playlist p where p.playlistname = '"+plname+"' and p.customerid = "+cid";

select p.playlistid from playlist p where  p.customerid = "+cid+" and p.playlistname = '"+plname+"';

insert into songplaylist values(null,"+psid+","+sid+");

select p.playlistid from playlist p where  p.customerid = "+cid+" and p.playlistname = '"+plname+"';

insert into podcastplaylist values(null,"+ppcid+","+pcid+");

select s.songlink from customer c join playlist p  join songplaylist spl  join song  s on(c.customerID = p.customerId and p.playlistId = spl.playlistId and spl.songId = s.songId) where c.customerId = "+cid";

## queries from jb connect

select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal  
where s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid and s.songartistid=1 order by sar.songartistname;
                   
select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal  
where s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid and s.songalbumid = 1 order by s.songgenere;

select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal 
where s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid group by sal.songalbumname;

select p.podcastid,p.podcasttitle,psp.podcastspeakername,pse.podcastseriesname,p.podcasttopic,p.podcastduration 
from podcast p join podcastspeaker psp join podcastseries pse                             
where p.podcastspeakerid=psp.podcastspeakerid and p.podcastseriesid = pse.podcastseriesid and psp.podcastspeakerid =2;

select p.podcastid,p.podcasttitle,psp.podcastspeakername,pse.podcastseriesname,p.podcasttopic,p.podcastduration 
from podcast p join podcastspeaker psp join podcastseries pse 
where p.podcastspeakerid=psp.podcastspeakerid and p.podcastseriesid = pse.podcastseriesid group by pse.podcastseriesname;
                            
select p.podcastid,p.podcasttitle,psp.podcastspeakername,pse.podcastseriesname,p.podcasttopic,p.podcastduration 
from podcast p join podcastspeaker psp join podcastseries pse 
where p.podcastspeakerid=psp.podcastspeakerid and p.podcastseriesid = pse.podcastseriesid group by p.podcasttopic;                     
				
select c.customerid from customer c where c.customeremail='" + email + "'  and c.customerpassword = '" + password + "';

select p.playlistid ,p.playlistname from playlist p where p.customerid = " + cid + " and p.playlistname = '" + plname + "';

select s.songid,s.songtitle,sar.songartistname,sal.songalbumname,s.songgenere ,s.songduration from song s join songartist  sar join songalbum sal 
where s.songid in (select spl.songid from  songplaylist spl where spl.playlistid in 
(select p.playlistid from playlist p where p.playlistid = "+pid+" and p.customerid ="+cid")) 
and s.songartistid = sar.songartistid and s.songalbumid=sal.songalbumid;









