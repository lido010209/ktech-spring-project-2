INSERT INTO board(type)
VALUES
('자유 게시판'),
('개발 게시판'),
('일상 게시판'),
('사건사고');

INSERT INTO article(title, content, writer, password, board_id)
VALUES
('The Future of AI', 'Artificial intelligence #ai #python is transforming industries, but what does the future hold?', 'Alice Johnson', 'alice', 1),
('Traveling Europe on a Budget', 'Exploring Europe doesn''t have to break the bank. Here are tips to travel on a budget.', 'John Smith', 'smith', 4),
('Healthy Eating Habits', 'Developing healthy eating habits can lead to a happier and healthier life.', 'Emily Davis', 'emily', 4),
('Introduction to Python', 'Python is a versatile language that is great for beginners and experts alike.', 'Michael Brown', '0102', 2),
('The Benefits of Yoga', 'Yoga offers numerous health benefits, including stress reduction and improved flexibility.', 'Sarah Lee', 'sarah', 4),
('A Guide to Meditation', 'Meditation can help clear your mind and reduce stress. Learn how to get started.', 'David Kim', '9876', 1),
('Exploring Quantum Physics', 'Quantum physics is a complex but fascinating field that challenges our understanding of the universe.', 'Rachel Green', 'rachel', 3),
('The Impact of Social Media', 'Social media has changed the way we communicate, #luna but what are the long-term effects?', 'James Wilson', 'wilson', 3),
('My universe', 'My life is the best', 'Luna Do', 'luna', 3),
('My world', 'My world is wide #fighting #mtlife', 'Luna Do', 'luna', 1),
('My family', 'My life is the best', 'Luna Do', 'luna', 2),
('Sustainable Living Tips', 'Living sustainably doesn''t have to be difficult. Here are some simple tips to get started.', 'Jessica White', 'white', 2),
('The Rise of Remote Work', 'Remote work is becoming the norm. How can companies and employees adapt?', 'James Wilson', 'james', 2),
('The Art of Public Speaking', 'Modern web development requires understanding the latest trends and best practices. Public speaking is a valuable skill. Here''s how to overcome your fear and improve.', 'Elizabeth Clark', 'Clark', 4),
('Sustainable Living Tips', 'Living sustainably doesn''t have to be difficult. Here are some simple tips to get started.', 'Jessica White', 'white', 2),
('Sustainable Living Tips', '#luna #living #tips', 'Jessica White', 'white', 2),
('Never give up', '#luna #fighting #me', 'Luna Do', '1234', 1),
('Good tips', '#luna #living #tips There are a lot of tips to survive here.', 'Jessica White', 'white', 2),
('Peaceful', 'My life is the best', 'Luna Do', 'luna', 3),
('Java', 'Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let programmers write once, run anywhere', 'Luna Do', 'luna', 1),
('Spring Boot', 'Spring Boot is an open-source #python #java Java framework used for programming standalone, production-grade #springboot #luna Spring-based applications with minimal effort. Spring Boot is a convention-over-configuration extension for the Spring Java platform intended to help minimize configuration concerns while creating Spring-based applications.', 'Luna Do', 'luna', 3),
('The Impact of AI on Healthcare', 'AI is revolutionizing healthcare by improving diagnosis accuracy and personalizing treatment plans.', 'Bob Smith', 'bob123', 1),
('Ethics in AI: A Growing Concern', 'As AI systems become more advanced, ethical considerations are becoming increasingly important.', 'Carol White', 'carol456', 4),
('AI in Education: A New Era', 'Artificial intelligence is transforming education by enabling personalized learning and automating administrative tasks.', 'David Lee', 'david789', 4),
('AI and the Future of Work', 'With AI automating many tasks, what will the future of work look like? Will it create more jobs or render them obsolete?', 'Eve Martinez', 'evepass', 2),
('AI in Finance: Risks and Opportunities', 'AI is changing the financial industry, from algorithmic trading to fraud detection, but it also brings new risks.', 'Frank Brown', 'frankly', 2),
('Understanding Machine Learning', 'Machine learning is a subset of AI that enables systems to learn and improve from experience without being explicitly programmed.', 'Grace Green', 'grace987', 1),
('The Role of AI in Cybersecurity', '#machine #luna As cyber threats evolve, AI is playing a crucial role in detecting and responding to attacks in real-time.', 'Henry Clark', 'henrysecure', 1),
('AI in Entertainment: The Next Frontier', '#ai #AI From content creation to audience analysis, AI is transforming the entertainment industry.', 'Isla King', 'isla321', 3),
('The Challenges of AI Integration', 'While AI offers many benefits, integrating it into existing systems and workflows presents significant challenges.', 'Jack Davis', 'jackstrong', 3),
('AI and Privacy: Striking a Balance', '#ai #article #python As AI becomes more pervasive, finding a balance between innovation and privacy protection is crucial.', 'Karen Walker', 'karenpass', 1);

INSERT INTO comment(content, writer, password, article_id)
VALUES
('Great article! I learned a lot about AI and its potential future impacts.', 'Alice Johnson', 'alice', 4),
('I totally agree with your points on healthy eating habits. Thanks for sharing!', 'John Smith', 'john', 4),
('This was very helpful, especially the tips on traveling Europe on a budget.', 'Emily Davis', 'Davis', 4),
('I found the guide on meditation really insightful. I’m going to try these techniques.', 'Michael Brown', 'michael', 6),
('Thank you for explaining quantum physics in such an easy-to-understand way!', 'Sarah Lee', 'sarah', 1),
('Cryptocurrency always confused me, but this article made it much clearer.', 'Daniel Moore', '123456', 1),
('The positive thinking techniques mentioned here have really helped me change my mindset.', 'Jessica White', 'jessica', 10),
('I started using some of the sustainable living tips from this article, and it’s made a difference.', 'Thomas Anderson', 'thomas', 3),
('Thank you for explaining quantum physics in such an easy-to-understand way!', 'Sarah Lee', 'sarah', 3),
('This was an insightful read. I appreciate the detailed explanation of machine learning concepts.', 'Bob Smith', 'bob123', 4),
('I think there are still many challenges to address, especially regarding AI ethics.', 'Carol White', 'carol456', 21),
('Interesting take on the future of AI. I would love to see more examples of real-world applications.', 'David Lee', 'david789', 21),
('The article was well-written, but I feel it could have gone deeper into the technical aspects.', 'Eve Martinez', 'evepass', 21),
('This article clarified many of my doubts about AI and its potential uses in different industries.', 'Frank Brown', 'frankly', 20),
('I am excited about the possibilities AI offers, especially in healthcare and education.', 'Grace Green', 'grace987', 20),
('A well-balanced discussion on AI. I appreciate how it addressed both the pros and cons.', 'Henry Clark', 'henrysecure', 19),
('I agree with the points made about AI safety. It’s crucial to consider these risks.', 'Isla King', 'isla321', 18),
('I found the discussion on AI in finance particularly interesting. Thanks for the insights!', 'Karen Walker', 'karenpass', 15),
('This article opened my eyes to the potential downsides of AI. Very informative.', 'Liam Roberts', 'liam456', 14),
('AI in entertainment could revolutionize how we experience media. Great read!', 'Mia Scott', 'mia789', 10),
('The article provided a good overview, but I wish it had more data to back up the claims.', 'Nathan Evans', 'nathan123', 7),
('Impressive work! The examples of AI in agriculture were eye-opening.', 'Olivia Harris', 'oliviasafe', 11),
('I’m still skeptical about AI replacing jobs, but this article made some compelling points.', 'Paul Mitchell', 'paulpass', 9),
('The future of AI in education seems promising. I’m excited to see where it goes.', 'Quinn Turner', 'quinnpass', 7),
('This was a comprehensive and well-researched article. I enjoyed reading it.', 'Rachel Adams', 'rachel789', 9),
('AI and its implications on privacy are concerning. Thanks for highlighting these issues.', 'Sam Wilson', 'samsecure', 4),
('Thanks for sharing this! It gave me a new perspective on AI advancements.', 'Jack Davis', 'jackstrong', 19);