package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "accounts")
public class AccountListWrapper {


//    @JacksonXmlProperty(localName = "account")
//    @JacksonXmlElementWrapper(useWrapping = false)
//    public List<Account> list;
//
//    public Accounts(Collection<Account> collection) {
//        this.list = new ArrayList<>(collection);
//    }

    @JacksonXmlProperty(localName = "account")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Collection<Account> collection;

    public AccountListWrapper(Collection<Account> collection) {
        this.collection = collection;
    }


    @JsonIgnore
    public boolean isNotEmpty(){
        return !this.collection.isEmpty();
    }
}
