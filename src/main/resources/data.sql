
INSERT INTO awssaasum.Quizzes (question, option1, option2, option3, option4, answer) VALUES
('Aws 루트계정 보안 권장사항은? (2개선택)', 'Aws 루트사용자 액세스키 생성 해당키 비지니스 소유자와만 공유', '액세스키 암호화후 amazon s3에 저장', '강력한 암호생성', 'MFA활성화', '4'),
('한국 스트리밍 회사가 유럽경기를 유렵사용자만 자사플랫폼으로 스트리밍 가능하게끔 해야함. 전세계 다른 국가들은 스트리밍경기에 대한 액세스 거부.
이러한 스트리밍 제한을 시행할 수 있는 옵션은? ', '1번 선택지', '2번 선택지', '3번 선택지', '4번 선택지', '2');

CREATE TABLE user_answer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,
    selected_option VARCHAR(255) NOT NULL,
    is_correct VARCHAR(255) NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (quiz_id) REFERENCES Quizzes(id)
);

CREATE SEQUENCE user_answer_seq;

select * from awssaasum.Quizzes

select * from awssaasum.user_answer;

ALTER TABLE awssaasum.Quizzes ADD COLUMN correct_answer VARCHAR(255);

ALTER TABLE awssaasum.Quizzes DROP COLUMN correct_answer;

TRUNCATE TABLE awssaasum.user_answer;
commit;

DELETE FROM awssaasum.Quizzes;

DELETE FROM awssaasum.userattempts WHERE quizId IN (SELECT id FROM awssaasum.Quizzes);



