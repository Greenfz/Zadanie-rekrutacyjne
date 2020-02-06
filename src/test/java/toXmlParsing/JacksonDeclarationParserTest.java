package toXmlParsing;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Account;
import model.AccountListWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class JacksonDeclarationParserTest {


    XmlMapper xmlMapper = spy(XmlMapper.class);
    @InjectMocks
    JacksonDeclarationParser jacksonDeclarationParser = new JacksonDeclarationParser();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void validAccountList_shouldWriteToFile() throws IOException {
        //  GIVEN
        File tempFile = temporaryFolder.newFile("xxx.xml");
        String filePath = tempFile.getPath();

        //  WHEN
        jacksonDeclarationParser.writeToXmlFile(getWrappedAccountsList(), filePath);
        String contentOfFile = new String(Files.readAllBytes(tempFile.toPath()));

        //  THEN
        assertEquals(VALIDXML, contentOfFile);

    }

    @Test
    public void emptyCollection_shouldNotWrite() throws IOException {
        //  GIVEN
        File tempFile = temporaryFolder.newFile("xxx.xml");
        String filePath = tempFile.getPath();
        AccountListWrapper emptyCollection = new AccountListWrapper(new ArrayList<>());

        //  WHEN
        jacksonDeclarationParser.writeToXmlFile(emptyCollection, filePath);

        //  THEN
        verifyZeroInteractions(xmlMapper);
    }


    private AccountListWrapper getWrappedAccountsList(){
        Account account1 = new Account("1", "a", "a", 22, LocalDate.now());
        Account account2 = new Account("22", "b", "b", 11, LocalDate.now());

        Set<Account> set = new TreeSet<>(Arrays.asList(account2, account1));

        return new AccountListWrapper(set);
    }

    private final String VALIDXML = "<?xml version='1.0' encoding='UTF-8'?>\r\n" +
            "<accounts>\r\n" +
            "  <account>\r\n" +
            "    <iban>1</iban>\r\n" +
            "    <name>a</name>\r\n" +
            "    <currency>a</currency>\r\n" +
            "    <balance>22.0</balance>\r\n" +
            "    <closingDate>2020-02-06</closingDate>\r\n" +
            "  </account>\r\n" +
            "  <account>\r\n" +
            "    <iban>22</iban>\r\n" +
            "    <name>b</name>\r\n" +
            "    <currency>b</currency>\r\n" +
            "    <balance>11.0</balance>\r\n" +
            "    <closingDate>2020-02-06</closingDate>\r\n" +
            "  </account>\r\n" +
            "</accounts>\r\n";
}


