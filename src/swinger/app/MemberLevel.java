package swinger.app;

public final class MemberLevel {

	public enum MemberLevels
	{ 
		eMemberLevelBase,
		eMemberLevelBronze,
		eMemberLevelSilver,
		eMemberLevelGold,
		eMemberLevelPlatinum,
		eMemberLevelDiamond
	}
	
	static int getEnumValue(MemberLevels iEnum)
	{
		int wEnumValue = 0;
		
		switch (iEnum)
		{
		case eMemberLevelBase:
			wEnumValue = 0;
			break;
		case eMemberLevelBronze:
			wEnumValue = 1;
			break;
		case eMemberLevelSilver:
			wEnumValue = 2;
			break;
		case eMemberLevelGold:
			wEnumValue = 3;
			break;
		case eMemberLevelPlatinum:
			wEnumValue = 4;
			break;
		case eMemberLevelDiamond:
			wEnumValue = 5;
			break;
		default:
			wEnumValue = -1;
		}
		
		return wEnumValue;
	}
}
