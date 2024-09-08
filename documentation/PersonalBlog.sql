CREATE DATABASE IF NOT EXISTS Personal_blog;
USE Personal_blog;

-- Tabela Donations
CREATE TABLE Donations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value FLOAT NOT NULL,
    createdAt DATETIME NOT NULL,
    receipt VARCHAR(255),
    CONSTRAINT UC_Receipt UNIQUE (receipt)
) engine=InnoDB;

-- Tabela User
CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    createdAt DATETIME NOT NULL,
    isBlocked BOOLEAN DEFAULT FALSE,
    isVerified BOOLEAN DEFAULT FALSE,
    isAdmin BOOLEAN DEFAULT FALSE
) engine=InnoDB;

-- Tabela Post
CREATE TABLE Post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME
) engine=InnoDB;

-- Tabela Comment
CREATE TABLE Comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME,
    parentId BIGINT,
    FOREIGN KEY (parentId) REFERENCES Comment(id) ON DELETE CASCADE,
    postId BIGINT,
    FOREIGN KEY (postId) REFERENCES Post(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Like (Curtidas)
CREATE TABLE `Like` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT,
    postId BIGINT,
    createdAt DATETIME NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (postId) REFERENCES Post(id) ON DELETE CASCADE
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
    FOREIGN KEY (postId) REFERENCES Post(id) ON DELETE CASCADE,
    FOREIGN KEY (tagId) REFERENCES Tags(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela Image (Imagens)
CREATE TABLE Image (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alt VARCHAR(255),
    content LONGBLOB NOT NULL,
    postId BIGINT,
    FOREIGN KEY (postId) REFERENCES Post(id) ON DELETE CASCADE
) engine=InnoDB;

-- Tabela PostReference (Referências entre Posts)
CREATE TABLE PostReference (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    postId BIGINT,
    referencedPostId BIGINT,
    FOREIGN KEY (postId) REFERENCES Post(id) ON DELETE CASCADE,
    FOREIGN KEY (referencedPostId) REFERENCES Post(id) ON DELETE CASCADE
) engine=InnoDB;
