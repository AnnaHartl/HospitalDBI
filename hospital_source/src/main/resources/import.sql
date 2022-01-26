insert into symptom(name)
    values ('Runny or stuffy nose'),
           ('Sore throat'),
           ('Cough'),
           ('Congestion'),
           ('Body aches'),
           ('Sneezing'),
           ('Fever'),
           ('Generally feeling unwell'),
           ('Aching muscles'),
           ('Sweats'),
           ('Headache'),
           ('Shortness of breath'),
           ('Tiredness and weakness'),
           ('Eye pain'),
           ('Vomiting and diarrhe'),
           ('Chest pain'),
           ('Confusion or changes in mental awareness'),
           ('Fatigue'),
           ('Lower than normal body temperature'),
           ('Nausea'),
           ('Red, watery eyes'),
           ('Itching of the nose'),
           ('Itching of the eyes'),
           ('Itching of the roof of the mouth'),
           ('Tingling in the mouth'),
           ('Swelling of the lips'),
           ('Swelling of the tongue'),
           ('Swelling of the face'),
           ('Swelling of the throat'),
           ('Hives'),
           ('Anaphylaxis'),
           ('Chest tightness'),
           ('Wheezing'),
           ('Itching'),
           ('A large area of swelling (edema) at the sting site'),
           ('Facial swelling'),
           ('Rash'),
           ('Redden'),
           ('Flake'),
           ('Peel'),
           ('Production of mucus (sputum), which can be clear, white, yellowish-gray or green in color'),
           ('Chest discomfort'),
           ('Chills'),
           ('A burning sensation in your chest (heartburn), usually after eating, which might be worse at night'),
           ('Difficulty swallowing'),
           ('Regurgitation of food or sour liquid'),
           ('Sensation of a lump in your throat');
           --(''),
           --(''),
           --(''),

insert into condition(name, description)
    values ('Common cold','The common cold is a viral infection of your nose and throat (upper respiratory tract). It''s usually harmless, although it might not feel that way. Many types of viruses can cause a common cold.'),
           ('Influenza (flu)','Influenza is a viral infection that attacks your respiratory system — your nose, throat and lungs. Influenza is commonly called the flu, but it''s not the same as stomach "flu" viruses that cause diarrhea and vomiting.'),
           ('Pneumonia','Pneumonia is an infection that inflames the air sacs in one or both lungs. The air sacs may fill with fluid or pus (purulent material), causing cough with phlegm or pus, fever, chills, and difficulty breathing. A variety of organisms, including bacteria, viruses and fungi, can cause pneumonia.'),
           ('Whooping cough','Whooping cough (pertussis) is a highly contagious respiratory tract infection. In many people, it''s marked by a severe hacking cough followed by a high-pitched intake of breath that sounds like "whoop."'),
           ('Hay fever',''),
           ('Food allergy',''),
           ('Insect sting allergy',''),
           ('Drug allergy',''),
           ('Atopic dermatitis','An allergic skin condition also called eczema'),
           ('Asthma','Asthma is a condition in which your airways narrow and swell and may produce extra mucus. This can make breathing difficult and trigger coughing, a whistling sound (wheezing) when you breathe out and shortness of breath.'),
           ('Bronchitis','Bronchitis is an inflammation of the lining of your bronchial tubes, which carry air to and from your lungs. People who have bronchitis often cough up thickened mucus, which can be discolored. Bronchitis may be either acute or chronic.'),
           ('Gastroesophageal reflux disease (GERD)','Gastroesophageal reflux disease (GERD) occurs when stomach acid frequently flows back into the tube connecting your mouth and stomach (esophagus). This backwash (acid reflux) can irritate the lining of your esophagus.');
           --('',''),

-- https://www.mayoclinic.org/diseases-conditions/common-cold/symptoms-causes/syc-20351605
insert into symptom_condition(condition_id, symptom_id)
    values (1,1),
           (1,2),
           (1,3),
           (1,4),
           (1,5),
           (1,6),
           (1,7),
           (1,11),
           (1,8);

-- https://www.mayoclinic.org/diseases-conditions/flu/symptoms-causes/syc-20351719
insert into symptom_condition(condition_id, symptom_id)
values (2,3),
       (2,7),
       (2,9),
       (2,10),
       (2,43),
       (2,11),
       (2,12),
       (2,13),
       (2,2),
       (2,14),
       (2,15);

-- https://www.mayoclinic.org/diseases-conditions/pneumonia/symptoms-causes/syc-20354204
insert into symptom_condition(condition_id, symptom_id)
values (3,16),
       (3,17),
       (3,3),
       (3,7),
       (3,18),
       (3,19),
       (3,20),
       (3,12);

-- https://www.mayoclinic.org/diseases-conditions/whooping-cough/symptoms-causes/syc-20378973
insert into symptom_condition(condition_id, symptom_id)
values (4,1),
       (4,4),
       (4,21),
       (4,7),
       (4,3);

-- https://www.mayoclinic.org/diseases-conditions/allergies/symptoms-causes/syc-20351497
insert into symptom_condition(condition_id, symptom_id)
values (5,6),
       (5,3),
       (5,22),
       (5,23),
       (5,24),
       (5,1),
       (5,21),
       (6,25),
       (6,26),
       (6,27),
       (6,28),
       (6,29),
       (6,30),
       (6,31),
       (7,31),
       (7,3),
       (7,32),
       (7,12),
       (7,33),
       (7,30),
       (7,34),
       (7,35),
       (8,30),
       (8,33),
       (8,34),
       (8,31),
       (8,36),
       (8,37),
       (9,34),
       (9,38),
       (9,39),
       (9,40);

-- https://www.mayoclinic.org/diseases-conditions/asthma/symptoms-causes/syc-20369653
insert into symptom_condition(condition_id, symptom_id)
values (10,12),
       (10,32),
       (10,33),
       (10,3);

-- https://www.mayoclinic.org/diseases-conditions/bronchitis/symptoms-causes/syc-20355566
insert into symptom_condition(condition_id, symptom_id)
values (11,3),
       (11,18),
       (11,12),
       (11,7),
       (11,41),
       (11,42),
       (11,43);

-- https://www.mayoclinic.org/diseases-conditions/gerd/symptoms-causes/syc-20361940
insert into symptom_condition(condition_id, symptom_id)
values (12,16),
       (12,44),
       (12,45),
       (12,46),
       (12,47);

-- Station Names from https://www.doh.wa.gov/ForPublicHealthandHealthcareProviders/HealthcareProfessionsandFacilities/HealthcareAssociatedInfections/HAIReports/TypesofHospitalUnits
insert into station(name)
values ('Neonatal Intensive Care'),
       ('Pediatric Intensive Care'),
       ('Coronary Care and Cardiothoracic'),
       ('Surgical Intensive Care'),
       ('Medical Intensive Care'),
       ('Long-term Intensive Care'),
       ('Neonatal'),
       ('Women and Infant Health'),
       ('Pediatric'),
       ('Post-critical'),
       ('Oncology'),
       ('Surgical'),
       ('Medical'),
       ('Rehabilitation Wards'),
       ('Long-term Care Wards');

-- Page 14 - https://www.ris.bka.gv.at/Dokumente/Bundesnormen/NOR40164799/I_34_OeVE_OeNORM_E_8007_2007-12-01.pdf
insert into roomtype(name)
    values ('1-Bedroom'),
           ('2-Bedroom'),
           ('3-Bedroom'),
           ('4-Bedroom'),
           ('Practice room for human and dental medicine'),
           ('Physical therapy room'),
           ('Hydro therapy room'),
           ('Massage room'),
           ('Room for radiological diagnostics and therapy'),
           ('Endoscopy room'),
           ('Dialysis room'),
           ('Intensive examination room'),
           ('Delivery room'),
           ('Operation prep room'),
           ('Operating room'),
           ('Recovery room'),
           ('Intensive care surveillance rooms');

