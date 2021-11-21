package converters;

import entity.SingerTwo;
import entity.SingerOne;
import org.springframework.core.convert.converter.Converter;

public class SingerToAnotherSingerConverter implements Converter<SingerOne, SingerTwo> {

    @Override
    public SingerTwo convert(SingerOne singerOne) {
        SingerTwo singerTwo = new SingerTwo();
        singerTwo.setFirstName(singerOne.getFirstName());
        singerTwo.setLastName(singerOne.getLastName());
        singerTwo.setBirthDate(singerOne.getBirthDate());
        singerTwo.setPersonalSite(singerOne.getPersonalSite());
        return singerTwo;
    }
}