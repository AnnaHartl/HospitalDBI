package at.htl.dto;

import at.htl.entity.Condition;

public class ConditionFilteredBySymptomDto {
    public Condition condition;
    public long amount;

    public ConditionFilteredBySymptomDto(Condition condition, long amount) {
        this.condition = condition;
        this.amount = amount;
    }

    public ConditionFilteredBySymptomDto(Condition condition) {
        this.condition = condition;
    }
}
