--Shashi Kumar Kadari Mallikarjuna
--Shivam Gupta

--/* Table structure for WritingGroups */--
CREATE TABLE WritingGroups (
  groupName VARCHAR(20) NOT NULL,  -- Name of the group
  headWriter VARCHAR(30) NOT NULL,  -- Name of the head writer
  yearFormed INTEGER NOT NULL,  --year in which the group was formed
  subject VARCHAR(30) NOT NULL,  --subject on which the group focuses on
  CONSTRAINT writingGroups_pk PRIMARY KEY (groupName) --primary key
);

--/* Table structure for Publishers */--
CREATE TABLE Publishers (
  publisherName VARCHAR(30) NOT NULL, --Name of the publisher
  publisherAddress VARCHAR(100) NOT NULL,  --Address of the publisher
  publisherPhone VARCHAR(15) NOT NULL,  --Publisher's phonenumber
  publisherEmail VARCHAR(50) NOT NULL,  --Publisher's email 
  CONSTRAINT publishers_pk PRIMARY KEY (publisherName)  --primary key
);

--/* Table structure for Books */--
CREATE TABLE Books (
  groupName VARCHAR(20) NOT NULL,  --Name of the group which wrote the book
  bookTitle VARCHAR(30) NOT NULL,  -- Book Title
  publisherName VARCHAR(30) NOT NULL,  --Name of the publisher who published that book
  yearPublished INTEGER NOT NULL,  --The year in which the book was published
  numberPages INTEGER NOT NULL,  --Number of pages in the book
  CONSTRAINT books_pk PRIMARY KEY (groupName, bookTitle), --Primary Key for the Books Table
  CONSTRAINT books_writingGroups_fk FOREIGN KEY (groupName) REFERENCES WritingGroups (groupName),  --Foreign Key from WritingGroups table
  CONSTRAINT books_ck UNIQUE (bookTitle, publisherName),  --candidate key for the Books table
  CONSTRAINT books_publishers_fk FOREIGN KEY (publisherName) REFERENCES Publishers (publisherName)  --Foreign Key from publishers table
) ;

--/* Populate the WritingGroups table with data */--
INSERT INTO WritingGroups(groupName, headWriter, yearFormed, subject) VALUES
('DC Comics', 'Jack Kirby', 1937, 'Adventure'),
('Marvel Comics', 'Stan Lee', 1961, 'Science fiction'),
('Arcadia Writers', 'Phoung Nguyen', 1999, 'Fantasy'),
('Soul Writers', 'Alvaro Monge', 2009, 'Autobiography'),
('Day Jammers', 'Neil deGrasse Tyson', 2016, 'Science'),
('Colorado Writers', 'Brian Cox', 2000, 'Short story'),
('Police Procedurals', 'Richard Dawkins', 1986, 'Biology'),
('California Club', 'Josh Hayter', 2019, 'All Genres'),
('San Diego Writers', 'John D', 2011, 'Journalism'),
('Bloomington-Normal', 'Pouye', 1997, 'Playwrights'),
('Tallahassee Writers', 'Todd Ebert', 2006, 'Paranormal'),
('American Writers', 'Goldstein Darin', 1977, 'Fiction'),
('Hawaiin Writers', 'Steve Gold', 1960, 'All Genres');

--/* Populate the Publishers table with data */--
INSERT INTO Publishers(publisherName, publisherAddress, publisherPhone, publisherEmail) VALUES
('Warner Bros.', '4239 Capitol Avenue Carthage, OR-97205', '616.562.0927', 'warner.bros@gmail.com'),
('Marvel Publishing', '256 Bond Street Providence, MA-02110', '419.823.4487', 'marvel.publishing@yahoo.com'),
('Arcadia Publishers', '2960 Arron Smith Drive Honolulu, GA-31635', '559.212.1504', 'arcadia.publishers@hotmail.com'),
('Soul Publishers', '1684 Wilson Street Havasu, SD-58436', '315.657.2977', 'soul.publishers@mail.com'),
('Jamming Publishers', '4806 Cabell Avenue Beltsville, VA-20705', '816.457.7732', 'jamming.publishers@outlook.com'),
('Love Publishers', '1664 West Fork Drive Hallandale Beach, FL 33009', '907.399.5242', 'love.publishers@live.com'),
('Bio Publishers', '4418 Joes Road Albany, NY 12207', '479.656.1145', 'bio.publishers@sharkmail.com'),
('Cali Publishers', '4420 Indiana Avenue Mililani, HI 96789', '312.855.2082', 'cali.publishers@baldeeagle.com'),
('San Publishers', '1534 Rhapsody Street Gainesville, FL 32601', '512.704.7393', 'san.publishers@webmail.com'),
('Lit Publishers', '212 Lindale Avenue Oakland, CA 94609', '678.446.4505', 'lit.publishers@protonmail.com'),
('One Publishers', '593 Cherry Camp Road Chicago, IL 60640', '520.423.8590', 'one.publishers@zoho.com'),
('Tale Publishers', '2984 Roane Avenue Bethesda, MD 20014', '773.547.4746', 'tale.publishers@aol.com'),
('Hawaiian Publishers', '1706 Goldleaf Lane Jersey City, NJ 07306', '732.853.8640', 'hawaiian.publishers@commonlook.com');

--/* Populate the Books table with data */--
INSERT INTO Books(groupName, bookTitle, publisherName, yearPublished, numberPages) VALUES
('DC Comics', 'Action Comics', 'Warner Bros.', 1938, 250),
('DC Comics', 'Adventures of Alan Ladd', 'Warner Bros.', 1949, 50),
('DC Comics', 'Amazons Attack!', 'Warner Bros.', 2007, 89),
('DC Comics', 'All Star Batman', 'Warner Bros.', 2011, 208),
('DC Comics', 'Ame-Comi Girls', 'Warner Bros.', 2013, 198),
('DC Comics', 'Animal Man', 'Warner Bros.', 2011, 260),
('DC Comics', 'Aquaman', 'Warner Bros.', 2016, 52),
('DC Comics', 'The Atom', 'Warner Bros.', 1962, 128),

('Marvel Comics', 'Amazing Fantasy', 'Marvel Publishing', 1962, 128),
('Marvel Comics', 'The Avengers', 'Marvel Publishing', 2001, 34),
('Marvel Comics', 'Beast', 'Marvel Publishing', 1997, 123),
('Marvel Comics', 'Beauty and the Beast', 'Marvel Publishing', 1984, 250),
('Marvel Comics', 'Before the Fantastic Four', 'Marvel Publishing', 2000, 134),
('Marvel Comics', 'Bug', 'Marvel Publishing', 1997, 34),
('Marvel Comics', 'Books of Doom', 'Marvel Publishing', 2006, 45),
('Marvel Comics', 'Blink', 'Marvel Publishing', 2001, 355),
('Marvel Comics', 'Blaze', 'Marvel Publishing', 1994, 249),
('Marvel Comics', 'Blood and Glory', 'Marvel Publishing', 1992, 76),

('Arcadia Writers', 'Black Sun', 'Arcadia Publishers', 2000, 234),
('Arcadia Writers', 'Black Panther Prelude', 'Arcadia Publishers', 2017, 123),
('Arcadia Writers', 'Black Panther', 'Arcadia Publishers', 2018, 222),
('Arcadia Writers', 'Barbie', 'Arcadia Publishers', 1991, 255),

('Soul Writers', 'The Glass Castle', 'Soul Publishers', 2009, 234),
('Soul Writers', 'Look Me in the Eye', 'Soul Publishers', 2012, 126),


('Day Jammers', 'Crazy liers', 'Jamming Publishers', 2017, 126),

('Colorado Writers', 'The Kiss Quotient', 'Love Publishers', 2000, 197),
('Colorado Writers', 'Still Me', 'Love Publishers', 2001, 215),
('Colorado Writers', 'A Princess in Theory', 'Love Publishers', 2009, 188),
('Colorado Writers', 'Hurts to Love You', 'Love Publishers', 2010, 134),

('Police Procedurals', 'Shelter in Place', 'Bio Publishers', 1987, 134),
('Police Procedurals', 'Tempest', 'Bio Publishers', 1992, 215),
('Police Procedurals', 'Intercepted', 'Bio Publishers', 1994, 76),
('Police Procedurals', 'The Royal Runaway', 'Bio Publishers', 1998, 187),
('Police Procedurals', 'Consumed', 'Bio Publishers', 2015, 167),
('Police Procedurals', 'Time’s Convert', 'Bio Publishers', 2018, 200),

('California Club', 'Come As You Are', 'Cali Publishers', 2019, 214),

('San Diego Writers', 'Why Not Tonight', 'San Publishers', 2011, 134),
('San Diego Writers', 'More Than Words', 'San Publishers', 2015, 112),
('San Diego Writers', 'From Here to You', 'San Publishers', 2018, 123),

('Bloomington-Normal', 'Hamlet', 'Lit Publishers', 1998, 24),
('Bloomington-Normal', 'Long Day Journeys', 'Lit Publishers', 2000, 249),
('Bloomington-Normal', 'Death of Salesman', 'Lit Publishers', 2002, 135),
('Bloomington-Normal', 'Oedipus Rex', 'Lit Publishers', 2010, 348),
('Bloomington-Normal', 'Angels in America', 'Lit Publishers', 2015, 222),
('Bloomington-Normal', 'The Glass Menagerie', 'Lit Publishers', 2015, 139),

('Tallahassee Writers', 'The Discovery Witches', 'One Publishers', 2006, 121),
('Tallahassee Writers', 'Dark Lover', 'One Publishers', 2008, 298),
('Tallahassee Writers', 'Fallen', 'One Publishers', 2010, 158),
('Tallahassee Writers', 'Evil Dead', 'One Publishers', 2015, 138),
('Tallahassee Writers', 'Vengeance Road', 'One Publishers', 2019, 189),

('American Writers', 'The Storyteller', 'Tale Publishers', 1977, 333),
('American Writers', 'Elective Affinities', 'Tale Publishers', 1982, 111),
('American Writers', 'Scorpion and Félix', 'Tale Publishers', 1997, 123),
('American Writers', 'Traces', 'Tale Publishers', 1998, 223),
('American Writers', 'Ginster', 'Tale Publishers', 1999, 325),
('American Writers', 'The Treasure of Indian Joe', 'Tale Publishers', 2010, 167),

('Hawaiin Writers', 'Dudley Zoo', 'Hawaiian Publishers', 2011, 197),
('Hawaiin Writers', 'Hello Stranger', 'Hawaiian Publishers', 2013, 168),
('Hawaiin Writers', 'Blood Type', 'Hawaiian Publishers', 2019, 279);


--drop table books;
--drop table publishers;
--drop table writinggroups;
