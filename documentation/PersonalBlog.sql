CREATE DATABASE IF NOT EXISTS Personal_blog;
USE Personal_blog;

-- Tabela Donations
CREATE TABLE Donations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value FLOAT NOT NULL,
    createdAt DATETIME NOT NULL,
    receipt MEDIUMBLOB,
    userId BIGINT NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela User
CREATE TABLE Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    createdAt DATETIME NOT NULL,
    isBlocked BOOLEAN DEFAULT FALSE,
    isVerified BOOLEAN DEFAULT FALSE,
    isAdmin BOOLEAN DEFAULT FALSE
) engine=InnoDB;

-- Tabela Post
CREATE TABLE Posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME,
    userId BIGINT NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Comment
CREATE TABLE Comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME,
    parentId BIGINT,
    FOREIGN KEY (parentId) REFERENCES Comments(id) ON DELETE CASCADE,
    postId BIGINT NOT NULL,
    FOREIGN KEY (postId) REFERENCES Posts(id) ON DELETE CASCADE,
    userId BIGINT NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Like (Curtidas)
CREATE TABLE Likes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT,
    postId BIGINT,
    createdAt DATETIME NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (postId) REFERENCES Posts(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Tags
CREATE TABLE Tags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
) engine=InnoDB;

-- Tabela PostTags (Associação entre Post e Tags)
CREATE TABLE PostTags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    postId BIGINT,
    tagId BIGINT,
    addedAt DATETIME NOT NULL,
    FOREIGN KEY (postId) REFERENCES Posts(id) ON DELETE CASCADE,
    FOREIGN KEY (tagId) REFERENCES Tags(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Image (Imagens)
CREATE TABLE Images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alt VARCHAR(255),
    content MEDIUMBLOB NOT NULL,
    postId BIGINT,
    FOREIGN KEY (postId) REFERENCES Posts(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela PostReference (Referências entre Posts)
CREATE TABLE PostReferences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    postId BIGINT,
    referencedPostId BIGINT,
    FOREIGN KEY (postId) REFERENCES Posts(id) ON DELETE CASCADE,
    FOREIGN KEY (referencedPostId) REFERENCES Posts(id) ON DELETE CASCADE
) engine=InnoDB;
