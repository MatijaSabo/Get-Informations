package com.unizg.foi.GetInformations.model.app;

public class HelloModel
{
    private String text;
    private String value;

    public HelloModel(String text) {
        this.text = text;
        this.value = "value";
    }

    public String getText()
    {
        return this.text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
