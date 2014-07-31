// David Lanouette
// Coding problem 1.4 for Phoenix Integration

#include <iostream>
#include <string>
#include <sstream> 


std::wstring convertToWide(std::string input)
{
	std::wstringstream ws;
	ws << input.c_str();
	std::wstring output = ws.str();

	return output;
}

int main(int argc, char** argv)
{
	std::string test = "this is the winter of our discontent";
	std::wstring wideTest = convertToWide(test);

	std::wcout << wideTest << std::endl;
}

