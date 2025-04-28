-- ruoli
INSERT INTO roles (id, name) VALUES 
(1, 'ADMIN'),
(2, 'USER');

-- utenti
INSERT INTO users (id, username, password) VALUES 
(1, 'adminuser', '{noop}admin123'),
(2, 'regularuser', '{noop}user123');

-- Associazione ruoli-utenti nella tabella ponte
INSERT INTO role_user (user_id, role_id) VALUES 
(1, 1), 
(2, 2); 


-- GENRES
INSERT INTO genre (id, name) VALUES (1, 'Pop');
INSERT INTO genre (id, name) VALUES (2, 'Rock');
INSERT INTO genre (id, name) VALUES (3, 'Jazz');
INSERT INTO genre (id, name) VALUES (4, 'Classical');
INSERT INTO genre (id, name) VALUES (5, 'Electronic');
INSERT INTO genre (id, name) VALUES (6, 'Hip Hop');

-- ARTISTS
INSERT INTO artists (id, name, biography, image_url) VALUES (1, 'The Beatles', 'The Beatles were an English rock band formed in Liverpool in 1960.', 'https://example.com/beatles.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (2, 'Beyoncé', 'Beyoncé Giselle Knowles-Carter is an American singer, songwriter, and actress.', 'https://example.com/beyonce.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (3, 'Miles Davis', 'Miles Davis was an American jazz trumpeter, bandleader, and composer.', 'https://example.com/miles.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (4, 'Avicii', 'Tim Bergling, known by his stage name Avicii, was a Swedish DJ, remixer, and music producer.', 'https://example.com/avicii.jpg');
INSERT INTO artists (id, name, biography, image_url) VALUES (5, 'Ludwig van Beethoven', 'Beethoven was a German composer and pianist, widely considered one of the greatest composers.', 'https://example.com/beethoven.jpg');

-- ALBUMS
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (1, 'Abbey Road', 'The Beatles'' iconic 1969 album', '1969-09-26', 'https://example.com/abbeyroad.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (2, 'Lemonade', 'Beyoncé''s 2016 visual album', '2016-04-23', 'https://example.com/lemonade.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (3, 'Kind of Blue', 'A seminal 1959 album by Miles Davis', '1959-08-17', 'https://example.com/kindofblue.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (4, 'True', 'Avicii''s debut studio album', '2013-09-13', 'https://example.com/true.jpg');
INSERT INTO albums (id, title, description, release_date, cover_url) VALUES (5, 'Symphony No.5', 'Beethoven''s iconic 5th symphony', '1808-12-22', 'https://example.com/beethoven5.jpg');

-- SONGS
INSERT INTO songs (id, title, artist_id, duration, cover_url, album_id) VALUES (1, 'Come Together', 1, 259, 'https://example.com/cometogether.jpg', 1);
INSERT INTO songs (id, title, artist_id, duration, cover_url, album_id) VALUES (2, 'Halo', 2, 261, 'https://example.com/halo.jpg', 2);
INSERT INTO songs (id, title, artist_id, duration, cover_url, album_id) VALUES (3, 'So What', 3, 544, 'https://example.com/sowhat.jpg', 3);
INSERT INTO songs (id, title, artist_id, duration, cover_url, album_id) VALUES (4, 'Wake Me Up', 4, 300, 'https://example.com/wakemeup.jpg', 4);
INSERT INTO songs (id, title, artist_id, duration, cover_url, album_id) VALUES (5, 'Ode to Joy', 5, 300, 'https://example.com/odetojoy.jpg', 5);

-- ALBUM_ARTIST (relazione)
INSERT INTO album_artist (album_id, artist_id) VALUES (1, 1);
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 2);
INSERT INTO album_artist (album_id, artist_id) VALUES (3, 3);
INSERT INTO album_artist (album_id, artist_id) VALUES (4, 4);
INSERT INTO album_artist (album_id, artist_id) VALUES (5, 5);

-- ALBUM_GENRE (relazione)
INSERT INTO album_genre (album_id, genre_id) VALUES (1, 2); -- Abbey Road -> Rock
INSERT INTO album_genre (album_id, genre_id) VALUES (2, 1); -- Lemonade -> Pop
INSERT INTO album_genre (album_id, genre_id) VALUES (3, 3); -- Kind of Blue -> Jazz
INSERT INTO album_genre (album_id, genre_id) VALUES (4, 5); -- True -> Electronic
INSERT INTO album_genre (album_id, genre_id) VALUES (5, 4); -- Symphony No.5 -> Classical

-- SONG_GENRE (relazione)
INSERT INTO song_genre (song_id, genre_id) VALUES (1, 2); -- Come Together -> Rock
INSERT INTO song_genre (song_id, genre_id) VALUES (2, 1); -- Halo -> Pop
INSERT INTO song_genre (song_id, genre_id) VALUES (3, 3); -- So What -> Jazz
INSERT INTO song_genre (song_id, genre_id) VALUES (4, 5); -- Wake Me Up -> Electronic
INSERT INTO song_genre (song_id, genre_id) VALUES (5, 4); -- Ode to Joy -> Classical
