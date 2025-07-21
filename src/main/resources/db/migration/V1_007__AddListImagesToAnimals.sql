-- 1. Yeni tablo: animal_images
CREATE TABLE animal_images (
    animal_id BIGINT NOT NULL,
    image_url TEXT NOT NULL,
    CONSTRAINT fk_animal_images FOREIGN KEY (animal_id)
        REFERENCES animals(id) ON DELETE CASCADE
);

-- 2. Mevcut image_url değerlerini kopyala
INSERT INTO animal_images (animal_id, image_url)
SELECT id, image_url FROM animals
WHERE image_url IS NOT NULL;

-- 2. animal tablosuna video alanı
ALTER TABLE animals
ADD COLUMN video_url TEXT;
