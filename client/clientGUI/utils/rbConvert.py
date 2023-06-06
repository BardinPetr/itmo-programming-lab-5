import os
import sys
from translate import Translator

BASE_LANG = "en"
TARGET_LANGS = []
TRANSLATORS = {i: Translator(from_lang=BASE_LANG, to_lang=i) for i in TARGET_LANGS}

class_template = lambda package, base_name, contents: \
    f"""package {package};

import java.util.ListResourceBundle;

public class {base_name} extends ListResourceBundle {{
    @Override
    protected Object[][] getContents() {{
        return new Object[][] {{
{contents}
        }};
    }}
}}"""


def translate(from_lang, to_lang, contents):
    if to_lang is None:
        return contents
    for k, v in contents.items():
        print(k, v, TRANSLATORS[to_lang].translate(v))
    return {k: TRANSLATORS[to_lang].translate(v) for k, v in contents.items()}


def main():
    package, target_dir = sys.argv[1:]

    for root, _, files in os.walk(target_dir):
        for f_path in files:
            full_path = f"{root}/{f_path}"
            if not f_path.endswith('.properties'): continue

            with open(full_path, 'r') as f:
                contents = f.read()

            contents = {(pair := line.split('='))[0]: pair[1] for i in contents.split('\n') if (line := i.strip())}

            base_name = f_path.split('.')[0]

            for target_lang in [None, *TARGET_LANGS]:
                translated_name = base_name + ('_' + target_lang if target_lang else '')
                target_file = f"{root}/{translated_name}.java"
                with open(target_file, 'w') as target_file:
                    contents_str = translate(BASE_LANG, target_lang, contents)
                    contents_str = [f'{12 * " "}{{"{k}", "{v}"}}' for k, v in contents_str.items()]
                    contents_str = ',\n'.join(contents_str)
                    text = class_template(package, translated_name, contents_str)
                    target_file.write(text)


if __name__ == '__main__':
    main()
