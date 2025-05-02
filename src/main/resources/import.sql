-- RUOLI
INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

-- UTENTI
INSERT INTO users (id, username, password) VALUES (1, 'adminuser', '{noop}admin123');
INSERT INTO users (id, username, password) VALUES (2, 'regularuser', '{noop}user123');

-- RUOLI-UTENTI
INSERT INTO role_user (user_id, role_id) VALUES (1, 1);
INSERT INTO role_user (user_id, role_id) VALUES (2, 2);

-- GENERI
INSERT INTO genre (id, name) VALUES (1, 'Pop');
INSERT INTO genre (id, name) VALUES (2, 'Rock');
INSERT INTO genre (id, name) VALUES (3, 'Jazz');
INSERT INTO genre (id, name) VALUES (4, 'Classical');
INSERT INTO genre (id, name) VALUES (5, 'Electronic');
INSERT INTO genre (id, name) VALUES (6, 'Hip Hop');

-- ARTISTI
INSERT INTO artists (id, name, biography, image_url) VALUES (1, 'The Beatles', 'The Beatles were an English rock band formed in Liverpool in 1960.', 'https://m.media-amazon.com/images/I/91Bdif9N84L._AC_UF1000,1000_QL80_.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (2, 'Beyoncé', 'Beyoncé Giselle Knowles-Carter is an American singer, songwriter, and actress.', 'https://upload.wikimedia.org/wikipedia/commons/1/14/Beyonc%C3%A9_-_Tottenham_Hotspur_Stadium_-_1st_June_2023_%286_of_118%29_%2852945304172%29_%28high_cropped%29.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (3, 'Miles Davis', 'Miles Davis was an American jazz trumpeter, bandleader, and composer.', 'https://cdn-p.smehost.net/sites/c5d2b1a28fd246bfafed3b8dbafc1352/wp-content/uploads/2022/04/milesdavis-1.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (4, 'Avicii', 'Tim Bergling, known by his stage name Avicii, was a Swedish DJ, remixer, and music producer.', 'https://i.scdn.co/image/ab6761610000e5ebae07171f989fb39736674113');
INSERT INTO artists (id, name, biography, image_url) VALUES (5, 'Ludwig van Beethoven', 'Beethoven was a German composer and pianist, widely considered one of the greatest composers.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Beethoven.jpg/960px-Beethoven.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (6, 'Linkin Park', 'Linkin Park is an American rock band from Agoura Hills, California, formed in 1996.', 'https://www.impericon.com/cdn/shop/files/20240911_linkinpark_pressefoto.jpg?v=1726035658&width=1548');

-- ALBUMS
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (1, 'Abbey Road', 'The Beatles'' iconic 1969 album', '1969-09-26', 'https://m.media-amazon.com/images/I/81sBKBIcwvL.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (2, 'Lemonade', 'Beyoncé''s 2016 visual album', '2016-04-23', 'https://m.media-amazon.com/images/I/81QYCfKUAML.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (3, 'Kind of Blue', 'A seminal 1959 album by Miles Davis', '1959-08-17', 'https://m.media-amazon.com/images/I/71UUU3OLX2L.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (4, 'True', 'Avicii''s debut studio album', '2013-09-13', 'https://m.media-amazon.com/images/I/617zXvijUBL.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (5, 'Symphony No.5', 'Beethoven''s iconic 5th symphony', '1808-12-22', 'https://i.scdn.co/image/ab67616d0000b27337c26a804db4202a6d4a4a4d');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (6, 'Hybrid Theory', 'Debut album by Linkin Park', '2000-10-24', 'https://m.media-amazon.com/images/I/81iC+O0ec2L._UF1000,1000_QL80_.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (7, 'Meteora', 'Second album by Linkin Park', '2003-03-25', 'https://m.media-amazon.com/images/I/71-nhMkC-vL._UF1000,1000_QL80_.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (8, 'Minutes to Midnight', 'Third studio album by Linkin Park', '2007-05-14', 'https://m.media-amazon.com/images/I/71zibYlETiL.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (9, 'A Thousand Suns', 'Experimental fourth album by Linkin Park', '2010-09-14', 'https://m.media-amazon.com/images/I/81Z8E8G1OEL._UF1000,1000_QL80_.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (10, 'Living Things', 'Fifth album by Linkin Park', '2012-06-20', 'https://m.media-amazon.com/images/I/71BV1XhB9WL._UF1000,1000_QL80_.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (11, 'The Hunting Party', 'Sixth studio album by Linkin Park', '2014-06-13', 'https://m.media-amazon.com/images/I/71t+4TJ6evL.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (12, 'One More Light', 'Seventh studio album by Linkin Park', '2017-05-19', 'https://m.media-amazon.com/images/I/41yOAfevc-L._UF1000,1000_QL80_.jpg');

-- SONGS
INSERT INTO songs (id, title, duration, album_id) VALUES (1, 'Come Together', 259, 1);
INSERT INTO songs (id, title, duration, album_id) VALUES (2, 'Halo', 261, 2);
INSERT INTO songs (id, title, duration, album_id) VALUES (3, 'So What', 544, 3);
INSERT INTO songs (id, title, duration, album_id) VALUES (4, 'Wake Me Up', 300, 4);
INSERT INTO songs (id, title, duration, album_id) VALUES (5, 'Ode to Joy', 300, 5);
INSERT INTO songs (id, title, duration, album_id) VALUES (6, 'In the End', 216, 6);
INSERT INTO songs (id, title, duration, album_id) VALUES (7, 'Numb', 185, 7);
INSERT INTO songs (id, title, duration, album_id) VALUES (8, 'Bleed It Out', 163, 8);
INSERT INTO songs (id, title, duration, album_id) VALUES (9, 'Waiting for the End', 231, 9);
INSERT INTO songs (id, title, duration, album_id) VALUES (10, 'Burn It Down', 231, 10);
INSERT INTO songs (id, title, duration, album_id) VALUES (11, 'Final Masquerade', 234, 11);
INSERT INTO songs (id, title, duration, album_id) VALUES (12, 'Heavy', 155, 12);

-- ALBUM_ARTIST
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 1);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 2);
INSERT INTO album_artist (album_id, artist_id) VALUES (3, 3);
INSERT INTO album_artist (album_id, artist_id) VALUES (4, 4);
INSERT INTO album_artist (album_id, artist_id) VALUES (5, 5);
INSERT INTO album_artist (album_id, artist_id) VALUES (6, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (7, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (8, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (9, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (10, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (11, 6);
INSERT INTO album_artist (album_id, artist_id) VALUES (12, 6);

-- ALBUM_GENRE
INSERT INTO album_genre (album_id, genre_id) VALUES (1, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (2, 1);
INSERT INTO album_genre (album_id, genre_id) VALUES (3, 3);
INSERT INTO album_genre (album_id, genre_id) VALUES (4, 5);
INSERT INTO album_genre (album_id, genre_id) VALUES (5, 4);
INSERT INTO album_genre (album_id, genre_id) VALUES (6, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (7, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (8, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (9, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (10, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (11, 2);
INSERT INTO album_genre (album_id, genre_id) VALUES (12, 2);

-- SONG_GENRE
INSERT INTO song_genre (song_id, genre_id) VALUES (1, 2);
INSERT INTO song_genre (song_id, genre_id) VALUES (2, 1);
INSERT INTO song_genre (song_id, genre_id) VALUES (3, 3);
INSERT INTO song_genre (song_id, genre_id) VALUES (4, 5);
INSERT INTO song_genre (song_id, genre_id) VALUES (5, 4);
INSERT INTO song_genre (song_id, genre_id) VALUES (6, 2);
INSERT INTO song_genre (song_id, genre_id) VALUES (7, 2);
INSERT INTO song_genre (song_id, genre_id) VALUES (8, 2);
INSERT INTO song_genre (song_id, genre_id) VALUES (9, 2);
INSERT INTO song_genre (song_id, genre_id) VALUES (10, 2);
INSERT INTO song_genre (song_id, genre_id) VALUES (11, 2);
INSERT INTO song_genre (song_id, genre_id) VALUES (12, 2);

-- SONG_ARTIST
INSERT INTO song_artist (song_id, artist_id) VALUES (1, 1);
INSERT INTO song_artist (song_id, artist_id) VALUES (2, 2);
INSERT INTO song_artist (song_id, artist_id) VALUES (3, 3);
INSERT INTO song_artist (song_id, artist_id) VALUES (4, 4);
INSERT INTO song_artist (song_id, artist_id) VALUES (5, 5);
INSERT INTO song_artist (song_id, artist_id) VALUES (6, 6);
INSERT INTO song_artist (song_id, artist_id) VALUES (7, 6);
INSERT INTO song_artist (song_id, artist_id) VALUES (8, 6);
INSERT INTO song_artist (song_id, artist_id) VALUES (9, 6);
INSERT INTO song_artist (song_id, artist_id) VALUES (10, 6);
INSERT INTO song_artist (song_id, artist_id) VALUES (11, 6);
INSERT INTO song_artist (song_id, artist_id) VALUES (12, 6);
