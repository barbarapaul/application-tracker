CREATE TABLE applications
(
  application_id BIGSERIAL PRIMARY KEY,
  name           TEXT
);

CREATE TABLE answers
(
  answer_id      BIGSERIAL PRIMARY KEY,
  application_id BIGINT REFERENCES applications (application_id),
  question_id    TEXT,
  answer         TEXT
);
