DROP DATABASE IF EXISTS vinylrecordshop;
CREATE DATABASE vinylrecordshop;
USE vinylrecordshop;

CREATE TABLE album (
	albumId INT NOT NULL AUTO_INCREMENT,
	CONSTRAINT pk_album 
		PRIMARY KEY (albumId),
	albumTitle VARCHAR(100) NOT NULL,
	label VARCHAR(50),
	releaseDate DATE,
	price DECIMAL(5,2)
   );

CREATE TABLE artist (
	artistId INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_artist
		PRIMARY KEY (artistId),
	artistFirstName VARCHAR(25),
    artistLastName VARCHAR(50) NOT NULL
	);
    
CREATE TABLE band (
	bandId INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_band
		PRIMARY KEY (bandId),
	bandName VARCHAR (50) NOT NULL
	);
    
CREATE TABLE song (
	songId INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_songId
		PRIMARY KEY (songId),
	songTitle VARCHAR(100) NOT NULL,
    videoUrl VARCHAR(100),
    bandId INT NOT NULL,
    CONSTRAINT fk_song_band
		FOREIGN KEY (bandId)
        REFERENCES band(bandId)
	);
    
CREATE TABLE songAlbum (
	songId INT NOT NULL,
	albumId INT NOT NULL,
    CONSTRAINT pk_songAlbum
		PRIMARY KEY (songId, albumId),
	CONSTRAINT fk_songAlbum_song
		FOREIGN KEY (songId)
        REFERENCES song(songId),
	CONSTRAINT fk_songAlbum_album
		FOREIGN KEY (albumId)
		REFERENCES album(albumId)
	);
    
CREATE TABLE bandArtist (
	bandId INT NOT NULL,
    artistId INT NOT NULL,
    CONSTRAINT pk_bandArtist
		PRIMARY KEY (bandId, artistId),
	CONSTRAINT fk_bandArtist_band
		FOREIGN KEY (bandId)
        REFERENCES band(bandId),
	CONSTRAINT fk_bandArtist_artist
		FOREIGN KEY (artistId)
		REFERENCES artist(artistId)
	);
