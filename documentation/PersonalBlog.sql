CREATE DATABASE IF NOT EXISTS Personal_blog;
USE Personal_blog;

-- Tabela Donations
CREATE TABLE Donations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value FLOAT NOT NULL,
    created_at DATETIME NOT NULL,
    receipt MEDIUMBLOB,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela User
CREATE TABLE Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    is_blocked BOOLEAN DEFAULT FALSE,
    is_verified BOOLEAN DEFAULT FALSE,
    is_admin BOOLEAN DEFAULT FALSE
) engine=InnoDB;

-- Tabela Post
CREATE TABLE Posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Comment
CREATE TABLE Comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES Comments(id) ON DELETE CASCADE,
    post_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Like (Curtidas)
CREATE TABLE Likes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Tags
CREATE TABLE Tags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
) engine=InnoDB;

-- Tabela PostTags (Associação entre Post e Tags)
CREATE TABLE Post_tag (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tags(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Image (Imagens)
CREATE TABLE Images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alt VARCHAR(255),
    content MEDIUMBLOB NOT NULL,
    post_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela PostReference (Referências dos posts)
CREATE TABLE PostReferences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    post_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE
) engine=InnoDB;