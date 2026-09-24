# Build the Vinyl Record Shop Database

### Overview

In this code-along, we will walk through the steps to create a database for a Vinyl Record Shop, a small business that specializes in vinyl albums.

As we work through the steps of creating the database structure, we will save the steps to a SQL script. SQL scripts can be used to back up an existing database or transfer a database to another server. At the end of this code-along, you will have a script that you can use (and reuse!) to create the Vinyl Music Shop database structure, including all tables and relationships, as defined in an ERD.

This code along includes the following steps:

* Examine the database's structure and organize the tables.
* Create the database.
* Create the primary tables.
* Create the related tables.
* Finalize the script.

### Step 1: Examine the Structure

Before starting to build any database, you should go through the steps to normalize the structure and create an ERD or a list of tables and fields that you can use as a roadmap for this process. We have already done this step for you.

Here is the ERD we will use for this activity.

![List of tables and fields follows the diagram](../normalize_vinyl/images/music-3nf.png)

Remember that fields in bold are not nullable.

Additional expectations:

* All primary key fields are auto-incrementing integer fields.
* The data types defined in the design steps must be converted to appropriate MySQL data types. For example, strings will use VARCHAR.

#### 1.1. Organize the Tables

Because of referential integrity, we must create the primary tables (tables that do not include foreign keys) before we can create the related tables that depend on existing primary keys.

Scan through the tables described above (in the ERD or the list) and identify the tables you think should be created first before going on.

The primary tables are:

* album
* artist
* band

These are primary tables because they do not have any foreign keys.

The related tables in the database include foreign keys that depend on other tables.

* song references band
* songAlbum references both song and album
* bandArtist references both band and artist

Note that we must prioritize the related tables, as well. In this case, the songAlbum table depends on the song table, so we must create the song table before we can create the songAlbum table. Because song depends on band, we must create the band table before we can create the song table.

You can create the primary tables in any order, as long as all of them are created before you create the dependent related tables. In this case, we will create the tables in the order listed above.

#### 1.2. Create the Script File

The end result of this activity will be a script that we can use to rebuild the database structure anytime we need to. To this end, create a new file in a code editor or text editor and save the file as vinylrecordshop-schema.sql.

* The term schema references the structure of the database. Including this in the file name lets us know the purpose of the file.
* The .sql extension is used for SQL scripts. RDBMS interfaces (like MySQL Workbench) can recognize these files and open them automatically.
* Add your name in a comment on the first line of the script, with a current date on the second line.

You can run the script directly in the RDBMS interface after saving any changes.

### Step 2: Create the Database

For the remaining steps in this activity, you should write the SQL statement in your SQL interface and test it to verify that it runs correctly. Once it works as expected, copy and paste it at the end of the script. You can then run the script to set up the database for the next SQL statement.

Before we can create tables, we must create a database space that holds the tables. In this case, we will name the database vinylrecordshop. However, we want to be sure that we are starting with an empty database. For this reason, we want to remove an existing database with that name before we create the new one.

Run the following command in MySQL:

        DROP DATABASE vinylrecordshop;

This statement will delete an existing database named vinylrecordshop. If you have this database in your MySQL instance, the statement will run without problem, but if the database does not exist, MySQL will throw an error because you cannot delete a database that does not exist.

We want our script to be flexible enough that anyone can use it, even if they are creating a brand new database rather than replacing an existing database. For this reason, we will modify the statement to:

        DROP DATABASE IF EXISTS vinylrecordshop;

The IF EXISTS clause tells MySQL to ignore the DROP command if the named database does not exist, so it will not throw an error.

Try both statements a few times to see how they work and what they do before going on. Remember that you can use the SHOW DATABASES; statement to see a list of available databases in MySQL to verify that the vinylrecordshop database is deleted.

Add the DROP IF EXISTS version of the statement under the comments you added to the .sql file and run the script to make sure it works.

Next, let's create the database:

        CREATE DATABASE vinylrecordshop;

Use SHOW DATABASES to verify that the database was created and then make it the active database:

        USE vinylrecordshop;

Add all three statements to the script. Run the script to verify that it works without error before going on.

### Step 3: Create the Primary Tables

At this point, you have a script that can delete an existing database named vinylrecordshop and replace it with a new (empty) database with the same name.

The next step is to create the tables themselves. Because we did the organizational step first, we know the order in which we should create the tables to avoid problems with referential integrity. For each table, you will define the table name and all fields in the table, along with appropriate field properties (data type, size, and NULL status) and key fields.

We'll start with the album table, which looks like:

* album
  * **albumId (PK) int**
  * **albumTitle string(100)**
  * label string(50)
  * releaseDate date
  * price float(5,2)

The CREATE statement in MySQL will look like:

        CREATE TABLE album (
            albumId INT AUTO_INCREMENT,
            albumTitle VARCHAR(100) NOT NULL,
            label VARCHAR(50),
            releaseDate DATE,
            price DECIMAL(5,2),
            CONSTRAINT pk_album
                PRIMARY KEY (albumId)
        );

This statement defines the table using the expected parameters:

* The albumId field auto-increments, which means that the database engine will automatically assign a sequential number to each new record, if no value is specified. This ensures that each record will have a different primary key value.
* The string values are defined using VARCHAR, specifying the maximum number of characters.
* releaseDate is a DATE field. In MySQL, the default format for a date is yyyy-mm-dd. While we need to know that when we add data to the field, right now, we just specify it as DATE.
* The price is a DECIMAL field with a maximum value of 999.99. which is appropriate for the data we are working with.
* price, releaseDate, and label are nullable fields.
* We define the PRIMARY KEY constraint on albumId.

Use DESCRIBE to verify that the table is defined correctly. If the statement works as expected, add it at the bottom of your script, save the script, and run it to make sure it works to delete and recreate the database and rebuild the table.

> Does the column order matter?
>
> One of the basic rules of relational database design is that the column order is not important. That said, we do generally put primary keys first because this helps speed up retrieval from the table. This is less important for foreign keys, but some database designers will add them immediately after the primary key.
>
> The CONSTRAINT definitions can also appear in any order, as long as the column the constraint references is defined first. This means that we could use the following statement to define the album table instead of the one we used above:
>
>        CREATE TABLE album (
>           albumId INT AUTO_INCREMENT,
>           CONSTRAINT pk_album 
>              PRIMARY KEY (albumId),
>           albumTitle VARCHAR(100) NOT NULL,
>           label VARCHAR(50),
>           releaseDate DATE,
>           price DECIMAL(5,2)
>        );
>
> Remember to put a comma after each column and constraint defined in a table (except the last one), because that is how MySQL differentiates each item in the table.

#### 3.1. On Your Own

Use the model for the album table to create the other primary tables (artist and band) on your own.

Here are the table descriptions:

* artist
  * **artistId (PK)**
  * artistFirstName string(25)
  * **artistLastName string(50)**


* band
  * **bandId (PK) int**
  * **bandName string(50)**

> Remember that you can use DESCRIBE tableName; to verify that a table's structure is correct, as well as DROP TABLE tableName; to delete an existing table if you need to rebuild it. After verifying that each CREATE TABLE statement works, add them to your SQL script.

### Step 4: Create the Related Tables

The related tables in the database include foreign keys that depend on other tables.

* song references band
* songAlbum references both song and album
* bandArtist references both band and artist

Because these tables depend on the primary tables, we must create the primary tables first. However, the songAlbum table also depends on the song table, so we must create the song table before we can create the songAlbum table.

#### 4.1. song Table

The song table includes the following fields:

* song
  * **songId (PK) int**
  * **songTitle string(100)**
  * videoUrl string(100)
  * **bandId (FK) int**

The first three columns include a primary key, a required field, and a nullable field.

        CREATE TABLE song (
            songId INT NOT NULL AUTO_INCREMENT,
            songTitle VARCHAR(100) NOT NULL,
            videoUrl VARCHAR(100),
            CONSTRAINT pk_song
                PRIMARY KEY (songId)
        );

The last column is a foreign key that references the bandId field in the band table. If you have not yet created the band table, you must do so before you can define the foreign key that references that table.

In MySQL, we need to complete two steps to define a foreign key.

1. Define the field as a normal field. In this case, we add it after the videoUrl column:

        DROP TABLE IF EXISTS song;
        CREATE TABLE song (
            songId INT NOT NULL AUTO_INCREMENT,
            songTitle VARCHAR(100) NOT NULL,
            videoUrl VARCHAR(100),
            bandId INT NOT NULL,
            CONSTRAINT pk_song
                PRIMARY KEY (songId)
        );

2. Add a foreign key constraint following the primary key constraint. This tells MySQL to enforce referential integrity on the bandId field in this table, so that any value entered in this field in the song table must first exist in the band table. We'll add this after the primary key constraint.  


        DROP TABLE IF EXISTS song;
        CREATE TABLE song (
            songId INT NOT NULL AUTO_INCREMENT,
            songTitle VARCHAR(100) NOT NULL,
            videoUrl VARCHAR(100),
            bandId INT NOT NULL,
            CONSTRAINT pk_song
                PRIMARY KEY (songId),
            CONSTRAINT fk_song_band
                FOREIGN KEY (bandID)
                REFERENCES band(bandId)
        );

Note that we name the foreign key constraint using both tables: the current table and the primary table. This ensures that the constraint name is unique, but it also helps document its purpose.

Verify that the table exists and that it includes the appropriate columns and settings and then add the CREATE TABLE statement to your script.

#### 4.2. songAlbum table

Now we're ready for the songAlbum table, which includes the following fields:

* songAlbum
  * **songId (PK, FK) int**
  * **albumId (PK, FK) int**

Two things to note here:

* The table has a composite key: the primary key includes both fields.
* Both of the fields are foreign keys related to separate tables.

We'll start by defining the columns. While both columns are included in the primary key, their values depend on the related fields in the song and album tables. This means that we do not want MySQL to number them automatically, so we will just define them as integers.

        CREATE TABLE songAlbum (
            songId INT,
            albumId INT
        );

We could specify that the fields are required, but because they are included in the primary key, entity integrity will enforce this.

Let's add the primary key constraint. When we have a single field in the primary key, we add just that field in the constraint. For a composite key, we list all fields included in the primary key, separated by commas.

While we could use an ALTER TABLE statement here, the goal is to have a script that can rebuild the database. We'll drop the existing table and rebuild it to include the primary key.

        DROP TABLE IF EXISTS songAlbum;
        CREATE TABLE songAlbum (
            songId INT,
            albumId INT,
            CONSTRAINT pk_songAlbum
                PRIMARY KEY (songId, albumId)
        );

Finally, we add both foreign key constraints:

        DROP TABLE IF EXISTS songAlbum;
        CREATE TABLE songAlbum (
            songId INT,
            albumId INT,
            CONSTRAINT pk_songAlbum
                PRIMARY KEY (songId, albumId),
            CONSTRAINT fk_songAlbum_song
                FOREIGN KEY (songId)
                REFERENCES song(songId),
            CONSTRAINT fk_songAlbum_album
                FOREIGN KEY (albumId)
                REFERENCES album(albumId)
        );

Verify that the table exists and that it includes the appropriate columns and settings before adding it to the script.

#### 4.3. bandArtist Table

Use the model for the songAlbum table to create the bandArtist table.

* bandArtist
  * **bandId (PK, FK) int**
  * **artistId (PK, FK) int**

Add the statement to your script once you have verified that it works as expected.
