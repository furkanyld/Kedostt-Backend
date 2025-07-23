-- 1. Hayvanlara ait video fileId için sütun
ALTER TABLE animals
ADD COLUMN video_file_id VARCHAR(255);

-- 2. image_file_id değerleri için yeni tablo
CREATE TABLE animal_image_file_ids (
    animal_id BIGINT NOT NULL,
    image_file_id VARCHAR(255),
    CONSTRAINT fk_animal_image_file FOREIGN KEY (animal_id)
        REFERENCES animals(id) ON DELETE CASCADE
);
